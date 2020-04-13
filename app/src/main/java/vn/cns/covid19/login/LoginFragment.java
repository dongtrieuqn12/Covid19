package vn.cns.covid19.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

import vn.cns.covid19.base.Lifecycle;
import vn.cns.covid19.fragment.BaseFragment;

public class LoginFragment extends BaseFragment implements LoginContract.View {

    private LoginContract.ViewModel loginViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LoginRepository loginRepository = LoginRepository.getInstance(Objects.requireNonNull(getActivity()).getApplicationContext());

        loginViewModel = new LoginViewModel(loginRepository);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return loginViewModel;
    }


    @Override
    public void launchHomeFragment() {

    }
}
