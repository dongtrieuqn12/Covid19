package vn.cns.covid19.login;

import vn.cns.covid19.base.Lifecycle;

public interface LoginContract {
    interface View extends Lifecycle.View {
        void launchHomeFragment();
    }

    interface ViewModel extends Lifecycle.ViewModel {
        void login(String samId);
    }
}
