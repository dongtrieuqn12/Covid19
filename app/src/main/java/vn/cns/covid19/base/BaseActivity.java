package vn.cns.covid19.base;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.Objects;

import vn.cns.covid19.R;
import vn.cns.covid19.Utils.Const;

public abstract class BaseActivity extends AppCompatActivity implements NfcAdapter.ReaderCallback {

    private AlertDialog dialog;

    @Override
    protected void onResume() {
        super.onResume();
        enableNfc();
        enableReaderMode();
    }

    @Override
    protected void onPause() {
        super.onPause();
        disableReaderMode();
    }

    private void enableReaderMode() {
        NfcAdapter mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        Bundle bundle = new Bundle();
        bundle.putInt(NfcAdapter.EXTRA_READER_PRESENCE_CHECK_DELAY, 3000);
        mNfcAdapter.enableReaderMode(this, this,
                NfcAdapter.FLAG_READER_NFC_A | NfcAdapter.FLAG_READER_NFC_B | NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK | NfcAdapter.FLAG_READER_NO_PLATFORM_SOUNDS,
                bundle);
    }

    private void disableReaderMode() {
        NfcAdapter mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter != null){
            mNfcAdapter.disableReaderMode(this);
        }
    }

    private void enableNfc() {
        NfcAdapter adapter = NfcAdapter.getDefaultAdapter(this);
        if (!adapter.isEnabled()) {
            if (dialog == null) {
                dialog = new AlertDialog.Builder(this)
                        .setMessage("NFC is required to communicate with a contactless smart card. Do you want to enable NFC now?")
                        .setTitle("Enable NFC")
                        .setPositiveButton(android.R.string.yes, (dialog, id) -> startActivity(new Intent(Settings.ACTION_NFC_SETTINGS)))
                        .setNegativeButton(android.R.string.no, (dialog, id) -> {})
                        .create();
            }
            dialog.show();
        }
    }

    @Override
    public void onTagDiscovered(Tag tag) {
        for(String tech : tag.getTechList()) {
            if(tech.equals(IsoDep.class.getName())){
                functionProcess(tag);
                break;
            }
        }
    }

    protected abstract void functionProcess(Tag tag);

    private static final String TAG = BaseActivity.class.getSimpleName();
    FragmentManager fragmentManager;
    String mCurrentFragmentTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
    }

    public void setFragment(Fragment fragment, @NonNull String tag, boolean clearFragmentBackStack) {
        if (tag.equals(getCurrentFragmentTag())) {
            return;
        }

        if (clearFragmentBackStack) {
            clearFragmentBackStack();
        }

        try {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Fragment currentFragment = getCurrentFragment();
            if (currentFragment != null) {
                fragmentTransaction.hide(currentFragment);
            }

            Fragment existingFragment = fragmentManager.findFragmentByTag(tag);
            if (existingFragment != null) {
                fragmentTransaction.show(existingFragment);
                Log.d(Const.TAG, "existingFragment != null");
            } else {
                fragmentTransaction.add(R.id.fragment_container, fragment, tag);
                Log.d(Const.TAG, "existingFragment == null");
            }

            fragmentTransaction.addToBackStack(tag);

            fragmentTransaction.commit();
        } catch (Exception ex) {
            Log.d(Const.TAG, ex.getMessage());
        }

        mCurrentFragmentTag = tag;
    }

    public void clearFragmentBackStack() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void detachFragment(String TAG) {
        if(mCurrentFragmentTag.equals(TAG)) {
            Fragment frg = null;
            frg = fragmentManager.findFragmentByTag(mCurrentFragmentTag);
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.detach(Objects.requireNonNull(frg));
            ft.attach(frg);
            ft.commit();
        }
    }

    public Fragment getCurrentFragment() {
        return mCurrentFragmentTag != null ? fragmentManager.findFragmentByTag(mCurrentFragmentTag) : null;
    }

    public Fragment getFragmentWithTag(String tag) {
        return fragmentManager.findFragmentByTag(tag);
    }

    public String getCurrentFragmentTag() {
        return mCurrentFragmentTag;
    }
}
