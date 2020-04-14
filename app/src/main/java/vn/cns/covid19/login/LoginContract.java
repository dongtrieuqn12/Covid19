package vn.cns.covid19.login;

import vn.cns.covid19.base.Lifecycle;
import vn.cns.covid19.model.config.ConfigResponse;

public interface LoginContract {
    interface View extends Lifecycle.View {
        void launchHomeFragment();
        void onFailedLogin(String errorMsg);
    }

    interface ViewModel extends Lifecycle.ViewModel {
        void login(String samId);
        void getToken(ConfigResponse configResponse);
    }
}
