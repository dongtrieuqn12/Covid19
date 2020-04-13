package vn.cns.covid19.login;

import vn.cns.covid19.base.Constants;
import vn.cns.covid19.base.Lifecycle;
import vn.cns.covid19.base.NetworkViewModel;

public class LoginViewModel extends NetworkViewModel implements LoginContract.ViewModel {

    private LoginContract.View viewCallback;
    private LoginRepository loginRepository;

    public LoginViewModel (LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public void onViewResumed() {
        @Constants.RequestState int requestState = getRequestState();
        if (requestState == Constants.REQUEST_SUCCEEDED) {
            onLoginCompleted();
        } else if (requestState == Constants.REQUEST_FAILED) {
            onLoginError(lastError);
        }
    }

    @Override
    public void onViewAttached(Lifecycle.View viewCallback) {
        this.viewCallback = (LoginContract.View) viewCallback;
    }

    @Override
    public void onViewDetached() {
        this.viewCallback = null;
    }

    @Override
    public boolean isRequestingInformation() {
        return false;
    }

    @Override
    public void login(String samId) {
        loginRepository.login(samId).subscribe(new LoginObserver());
    }

    private void onLoginCompleted() {
        if (viewCallback != null) {
            viewCallback.launchHomeFragment();
        }
    }

    private void onLoginError(Throwable e) {

    }

    private class LoginObserver extends MaybeNetworkObserver<Object> {
        @Override
        public void onSuccess(Object object) {
            super.onSuccess(object);
            onLoginCompleted();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            onLoginError(e);
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }
}
