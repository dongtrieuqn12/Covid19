package vn.cns.covid19.orders;

import android.util.Log;

import com.google.gson.Gson;

import java.util.Objects;

import vn.cns.covid19.Utils.Const;
import vn.cns.covid19.base.Lifecycle;
import vn.cns.covid19.base.NetworkViewModel;
import vn.cns.covid19.model.orders.OrdersResponse;

public class OrderViewModel extends NetworkViewModel implements OrderContract.ViewModel {

    private OrderContract.View viewCallback;
    private OrderRepository orderRepository;

    public OrderViewModel (OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void onViewResumed() {

    }

    @Override
    public void onViewAttached(Lifecycle.View viewCallback) {
        this.viewCallback = (OrderContract.View) viewCallback;
    }

    @Override
    public void onViewDetached() {
        viewCallback = null;
    }

    @Override
    public boolean isRequestingInformation() {
        return false;
    }

    @Override
    public void getOrders(String customerCode) {
        orderRepository.getOrders(customerCode).subscribe(new OrderSubscriber());
    }

    private class OrderSubscriber extends MaybeNetworkObserver<OrdersResponse> {
        @Override
        public void onSuccess(OrdersResponse ordersResponse) {
            super.onSuccess(ordersResponse);
            Log.d(Const.TAG,"response: " + new Gson().toJson(ordersResponse));
            viewCallback.updateOrders(ordersResponse);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            Log.d(Const.TAG, Objects.requireNonNull(e.getMessage()));
            viewCallback.onFailed();
//            viewCallback.showFailedDialog(e.getMessage());
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }
}
