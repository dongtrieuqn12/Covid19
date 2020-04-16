package vn.cns.covid19.orders;

import vn.cns.covid19.base.Lifecycle;
import vn.cns.covid19.model.customer.CustomerResponse;
import vn.cns.covid19.model.orders.OrdersResponse;

public interface OrderContract {
    interface View extends Lifecycle.View {
        void updateOrders (OrdersResponse ordersResponse);
        void updateCustomer (CustomerResponse customerResponse);
        void onFailed ();
    }

    interface ViewModel extends Lifecycle.ViewModel {
        void getOrders(String customerCode);
    }
}
