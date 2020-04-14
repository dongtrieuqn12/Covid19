package vn.cns.covid19.home;

import vn.cns.covid19.base.Lifecycle;
import vn.cns.covid19.base.NetworkViewModel;

public class HomeViewModel extends NetworkViewModel implements HomeContract.ViewModel {

    private HomeContract.View viewCallback;
    private HomeRepository homeRepository;

    public HomeViewModel (HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    @Override
    public void onViewResumed() {

    }

    @Override
    public void onViewAttached(Lifecycle.View viewCallback) {
        this.viewCallback = (HomeContract.View) viewCallback;
        this.viewCallback.showAnimation();
    }

    @Override
    public void onViewDetached() {
        this.viewCallback = null;
    }

    @Override
    public boolean isRequestingInformation() {
        return false;
    }
}
