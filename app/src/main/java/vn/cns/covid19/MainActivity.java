package vn.cns.covid19;

import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import vn.cns.covid19.base.BaseActivity;
import vn.cns.covid19.databinding.ActivityMainBinding;
import vn.cns.covid19.home.HomeFragment;
import vn.cns.covid19.login.LoginFragment;
import vn.cns.covid19.orders.OrderFragment;

public class MainActivity extends BaseActivity implements LoginFragment.AnonymousUserDetector {

    private ActivityMainBinding activityMainBinding;

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
        runOnUiThread(() -> setFragment(OrderFragment.newInstance(),OrderFragment.TAG,false));
    }

    @Override
    public void onUserLoginSuccess() {
        Toast.makeText(this,"login success", Toast.LENGTH_LONG).show();
        activityMainBinding.toolbar.setVisibility(View.VISIBLE);
        setTitle(getResources().getString(R.string.vinaId));
        setFragment(HomeFragment.newInstance(),HomeFragment.TAG, true);
    }
}
