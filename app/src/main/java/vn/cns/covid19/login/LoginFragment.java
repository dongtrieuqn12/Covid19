package vn.cns.covid19.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import java.util.Objects;

import vn.cns.covid19.R;
import vn.cns.covid19.Utils.PreferencesUtils;
import vn.cns.covid19.base.Lifecycle;
import vn.cns.covid19.databinding.FragmentLoginBinding;
import vn.cns.covid19.fragment.BaseFragment;
import vn.cns.covid19.model.config.ConfigResponse;

public class LoginFragment extends BaseFragment implements LoginContract.View {

    public static final String TAG = LoginFragment.class.getSimpleName();

    public interface AnonymousUserDetector {
        void onUserLoginSuccess();
    }

    private LoginContract.ViewModel loginViewModel;
    private FragmentLoginBinding fragmentLoginBinding;
    private AnonymousUserDetector listener;
    private ConfigResponse configResponse;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LoginRepository loginRepository = LoginRepository.getInstance(Objects.requireNonNull(getActivity()).getApplicationContext());
        loginViewModel = new LoginViewModel(loginRepository);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        fragmentLoginBinding = DataBindingUtil.inflate(LayoutInflater.from(Objects.requireNonNull(container).getContext()), R.layout.fragment_login,container,false);
        fragmentLoginBinding = FragmentLoginBinding.inflate(inflater,container,false);
        LoginCLickHandler mClickHandler = new LoginCLickHandler();
        fragmentLoginBinding.setClickHandler(mClickHandler);
        configResponse = PreferencesUtils.getInstance().loadConfig();
        if (configResponse != null) {
            fragmentLoginBinding.password.setText(configResponse.getSamId());
        }
        return fragmentLoginBinding.getRoot();
    }

    public void setListener(AnonymousUserDetector listener) {
        this.listener = listener;
    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return loginViewModel;
    }


    @Override
    public void launchHomeFragment() {
        listener.onUserLoginSuccess();
    }

    @Override
    public void onFailedLogin(String errorMsg) {
        showFailedDialog(errorMsg);
    }

    private void attemptLogin() {
        // Reset errors.
        fragmentLoginBinding.password.setError(null);

        // Store values at the time of the login attempt.
        String samId = Objects.requireNonNull(fragmentLoginBinding.password.getText()).toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(samId)) {
            fragmentLoginBinding.password.setError(getString(R.string.error_field_required));
            focusView = fragmentLoginBinding.password;
            cancel = true;
        } else if (!isSamIdValid(samId)) {
            fragmentLoginBinding.password.setError(getString(R.string.error_invalid_email));
            focusView = fragmentLoginBinding.password;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            if (configResponse != null) {
                loginViewModel.getToken(configResponse);
            } else {
                loginViewModel.login(samId);
            }
        }
    }

    private boolean isSamIdValid(String samId) {
        //TODO: Replace this with your own logic
        return samId.length() > 0;
    }

    public class LoginCLickHandler {
        public void onLoginClick (View view) {
            attemptLogin();
        }
    }
}
