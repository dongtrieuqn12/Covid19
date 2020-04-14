package vn.cns.covid19.orders;

import androidx.lifecycle.LiveData;

import java.util.List;

import vn.cns.covid19.base.Lifecycle;
import vn.cns.covid19.model.orders.OrderData;

public interface OrderContract {
    interface View extends Lifecycle.View {

    }

    interface ViewModel extends Lifecycle.ViewModel {
        void getOrders(String customerCode);
        LiveData<List<OrderData>> getOrdersData();
    }
}
