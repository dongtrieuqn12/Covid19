package vn.cns.covid19;

import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import vn.cns.covid19.base.BaseActivity;
import vn.cns.covid19.base.NFCReader;
import vn.cns.covid19.databinding.ActivityMainBinding;
import vn.cns.covid19.detail.DetailFragment;
import vn.cns.covid19.home.HomeFragment;
import vn.cns.covid19.login.LoginFragment;
import vn.cns.covid19.model.orders.OrderData;
import vn.cns.covid19.orders.OrderFragment;

public class MainActivity extends BaseActivity implements LoginFragment.AnonymousUserDetector, OrderFragment.OnUserSelectOrderData {

    private ActivityMainBinding activityMainBinding;
    public MutableLiveData<String> data = new MutableLiveData<>();
    public String customerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        setSupportActionBar(activityMainBinding.toolbar);
        activityMainBinding.toolbar.setVisibility(View.GONE);

        LoginFragment loginFragment = LoginFragment.newInstance();
        loginFragment.setListener(this);
        setFragment(loginFragment,LoginFragment.TAG,true);
    }

    @Override
    protected void functionProcess(Tag tag) {
        data.postValue(customerCode);
        try {
            NFCReader nfcReader = NFCReader.getInstance(IsoDep.get(tag));
            customerId = nfcReader.readCustomerID(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        runOnUiThread(() -> {
            OrderFragment orderFragment = OrderFragment.newInstance();
            orderFragment.setListener(this);
            setTitle("Chương trình hỗ trợ TPHCM");
            setFragment(orderFragment,OrderFragment.TAG,false);
        });
    }

    @Override
    public void onUserLoginSuccess() {
        Toast.makeText(this,"login success", Toast.LENGTH_LONG).show();
        activityMainBinding.toolbar.setVisibility(View.VISIBLE);
        setTitle(getResources().getString(R.string.vinaId));
        setTitle("Chương trình hỗ trợ TPHCM");
        setFragment(HomeFragment.newInstance(),HomeFragment.TAG, true);
    }

    @Override
    public void onSelectOrderData(OrderData orderData) {
        DetailFragment detailFragment = new DetailFragment(orderData);
        setFragment(detailFragment,DetailFragment.TAG,false);
        setTitle("Chi tiết " + orderData.getCode() + ", " + orderData.getCreatedDate().substring(0,10));
    }
}
