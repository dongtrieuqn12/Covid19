package vn.cns.covid19.detail;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Maybe;
import retrofit2.Retrofit;
import vn.cns.covid19.Utils.Const;
import vn.cns.covid19.Utils.DateUtils;
import vn.cns.covid19.Utils.PreferencesUtils;
import vn.cns.covid19.model.orders.NoteOrder;
import vn.cns.covid19.model.orders.OrderData;
import vn.cns.covid19.model.orders.OrderDetail;
import vn.cns.covid19.model.updateOrder.OrderUpdateRequest;
import vn.cns.covid19.model.updateOrder.UpdateOrderAPIService;
import vn.cns.covid19.services.APIUtils;

public class DetaiRepository {
    private static DetaiRepository instance;
    private UpdateOrderAPIService updateOrderAPIService;

    private DetaiRepository (Context context) {
        APIUtils apiUtils = new APIUtils(Const.KIOT_HOST);
        Retrofit retrofit = apiUtils.getService();
        updateOrderAPIService = new UpdateOrderAPIService(retrofit, PreferencesUtils.getInstance());
    }

    public static DetaiRepository getInstance(Context context) {
        synchronized (DetaiRepository.class) {
            if (instance == null) {
                instance = new DetaiRepository(context);
            }

            return instance;
        }
    }

    public boolean isUpdateRequest () {
        return updateOrderAPIService.isUpdateOrder();
    }

    public Maybe<OrderData> updateOrder (OrderData orderData, OrderDetail orderDetail) {
        return updateOrderAPIService.updateOrder(createBodyUpdateRequest(orderData,orderDetail),orderData.getId());
    }

    private OrderUpdateRequest createBodyUpdateRequest (OrderData orderData, OrderDetail orderDetail) {
        OrderUpdateRequest orderUpdateRequest = new OrderUpdateRequest();
        List<OrderDetail> orderDetails = orderData.getOrderDetails();
        if (orderDetail == null) {
            orderUpdateRequest.setMakeInvoice(true);
            for (OrderDetail object : orderDetails) {
                object.setNote(new Gson().toJson(new NoteOrder(true, DateUtils.GetCurrentDateTime("dd:MM:yyyy HH:mm:ss"))));
            }
            orderUpdateRequest.setOrderDetails(orderDetails);
            return orderUpdateRequest;
        } else {
            int count = 0;
            for (OrderDetail object : orderData.getOrderDetails()) {
                if (object.getNote() != null) {
                    if (!object.getNote().equals("")) {
                        count++;
                    }
                } else {
                    count++;
                }
            }

            Log.d(Const.TAG, "count = " + count);

            if (count == orderData.getOrderDetails().size() - 1) {
                orderUpdateRequest.setMakeInvoice(true);
                int index = orderDetails.indexOf(orderDetail);
                orderDetails.get(index).setNote(new Gson().toJson(new NoteOrder(true, DateUtils.GetCurrentDateTime("dd:MM:yyyy HH:mm:ss"))));
                orderUpdateRequest.setOrderDetails(orderDetails);
            } else {
                int index = orderDetails.indexOf(orderDetail);
                orderDetails.get(index).setNote(new Gson().toJson(new NoteOrder(true, DateUtils.GetCurrentDateTime("dd:MM:yyyy HH:mm:ss"))));
                orderUpdateRequest.setOrderDetails(orderDetails);
            }
            return orderUpdateRequest;
        }
    }
}
