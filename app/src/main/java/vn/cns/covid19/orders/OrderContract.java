package vn.cns.covid19.orders;

import android.graphics.Bitmap;

import vn.cns.covid19.base.Lifecycle;
import vn.cns.covid19.model.customer.CustomerResponse;
import vn.cns.covid19.model.orders.OrdersResponse;

public interface OrderContract {
    interface View extends Lifecycle.View {
        void updateOrders (OrdersResponse ordersResponse);
        void updateCustomer (CustomerResponse customerResponse);
        void onFailed ();
        void updateImage (Bitmap image);
    }

    interface ViewModel extends Lifecycle.ViewModel {
        void getOrders(String customerCode);
        void downLoadImage (String url);
    }
}
