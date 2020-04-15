package vn.cns.covid19.detail;

import vn.cns.covid19.base.Lifecycle;
import vn.cns.covid19.model.orders.OrderData;
import vn.cns.covid19.model.orders.OrderDetail;

public interface DetailContract {
    interface View extends Lifecycle.View {
        void updateOrder (OrderData orderData);
        void onFailed();
    }

    interface ViewModel extends Lifecycle.ViewModel {
        void requestUpdate (OrderData orderData, OrderDetail orderDetail);
    }
}
