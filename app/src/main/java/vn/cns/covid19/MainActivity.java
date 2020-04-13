package vn.cns.covid19;

import android.nfc.Tag;
import android.os.Bundle;
import vn.cns.covid19.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_fragment);
    }

    @Override
    protected void functionProcess(Tag tag) {

    }
}
