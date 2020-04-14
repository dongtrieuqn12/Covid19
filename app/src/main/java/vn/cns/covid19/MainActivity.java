package vn.cns.covid19;

import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import vn.cns.covid19.Utils.PreferencesUtils;
import vn.cns.covid19.base.BaseActivity;
import vn.cns.covid19.databinding.ActivityMainBinding;
import vn.cns.covid19.login.LoginFragment;

public class MainActivity extends BaseActivity implements LoginFragment.AnonymousUserDetector {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        activityMainBinding.toolbar.setVisibility(View.GONE);

        LoginFragment loginFragment = LoginFragment.newInstance();
        loginFragment.setListener(this);
        setFragment(loginFragment,LoginFragment.TAG,true);
    }

    @Override
    protected void functionProcess(Tag tag) {

    }

    @Override
    public void onUserLoginSuccess() {
        Toast.makeText(this,"login success", Toast.LENGTH_LONG).show();
    }
}
