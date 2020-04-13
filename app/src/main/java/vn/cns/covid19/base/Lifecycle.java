package vn.cns.covid19.base;

public interface Lifecycle {
    interface View {
        void showLoadingDialog();

        void dismissLoadingDialog();

        void showFailedDialog(String errorMessage);

        void showAnimation();

        void showAnimationCard();

        void CountTimeOut();

        void ShowToast(String message);
    }

    interface ViewModel {
        void onViewResumed();
        void onViewAttached(Lifecycle.View viewCallback);
        void onViewDetached();
    }
}
