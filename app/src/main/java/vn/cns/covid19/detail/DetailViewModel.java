package vn.cns.covid19.detail;

import vn.cns.covid19.base.Lifecycle;
import vn.cns.covid19.base.NetworkViewModel;
import vn.cns.covid19.model.orders.OrderData;
import vn.cns.covid19.model.orders.OrderDetail;

public class DetailViewModel extends NetworkViewModel implements DetailContract.ViewModel {

    private DetailContract.View viewCallback;
    private DetaiRepository detaiRepository;

    public DetailViewModel (DetaiRepository detaiRepository) {
        this.detaiRepository = detaiRepository;
    }

    @Override
    public void onViewResumed() {

    }

    @Override
    public void onViewAttached(Lifecycle.View viewCallback) {
        this.viewCallback = (DetailContract.View) viewCallback;
    }

    @Override
    public void onViewDetached() {
        this.viewCallback = null;
    }

    @Override
    public boolean isRequestingInformation() {
        return detaiRepository.isUpdateRequest();
    }

    @Override
    public void requestUpdate(OrderData orderData, OrderDetail orderDetail) {
        detaiRepository.updateOrder(orderData,orderDetail).subscribe(new UpdateOrderObserver());
    }

    private class UpdateOrderObserver extends MaybeNetworkObserver<OrderData> {
        @Override
        public void onSuccess(OrderData orderData) {
            super.onSuccess(orderData);
            viewCallback.dismissLoadingDialog();
            viewCallback.updateOrder(orderData);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            viewCallback.dismissLoadingDialog();
            viewCallback.onFailed();
            viewCallback.showFailedDialog(e.getMessage());
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }
}
