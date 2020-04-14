package vn.cns.covid19.orders;

import androidx.lifecycle.LiveData;

import java.util.List;

import vn.cns.covid19.base.Lifecycle;
import vn.cns.covid19.base.NetworkViewModel;
import vn.cns.covid19.model.orders.OrderData;

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
        orderRepository.getOrders(customerCode);
    }

    @Override
    public LiveData<List<OrderData>> getOrdersData() {
        return orderRepository.getListMutableLiveData();
    }
}
