package vn.cns.covid19.model.updateOrder;

import android.util.Log;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import vn.cns.covid19.Utils.Const;
import vn.cns.covid19.Utils.PreferencesUtils;
import vn.cns.covid19.model.orders.OrderData;

public class UpdateOrderAPIService {
    private IRequestUpdateOrderAPI requestUpdateOrderAPI;
    private boolean isUpdateOrder;
    private PreferencesUtils preferencesUtils;

    public UpdateOrderAPIService (Retrofit retrofit, PreferencesUtils preferencesUtils) {
        requestUpdateOrderAPI = retrofit.create(IRequestUpdateOrderAPI.class);
        this.preferencesUtils = preferencesUtils;
    }
    public boolean isUpdateOrder () {
        return isUpdateOrder;
    }

    public Maybe<OrderData> updateOrder (OrderUpdateRequest body, int orderId) {
        Log.d(Const.TAG,new Gson().toJson(body));
        Log.d(Const.TAG,"id: " + orderId);
        Map<String,String> header = new HashMap<>();
        header.put(Const.AUTHORIZATION, preferencesUtils.getToken());
        header.put(Const.RETAILER, preferencesUtils.loadConfig().getKiotConfig().getRetailer());
        return requestUpdateOrderAPI.UPDATE_ORDER_EXECUTE(header,orderId,body)
                .doOnSubscribe(disposable -> isUpdateOrder = true)
                .doOnTerminate(() -> isUpdateOrder = false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .singleElement();
    }
}
