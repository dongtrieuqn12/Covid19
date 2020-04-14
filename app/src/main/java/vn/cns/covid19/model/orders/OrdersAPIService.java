package vn.cns.covid19.model.orders;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import vn.cns.covid19.Utils.Const;
import vn.cns.covid19.Utils.PreferencesUtils;

public class OrdersAPIService {
    private IDataOrdersAPI dataOrdersAPI;
    private boolean isRequestOrder;
    private PreferencesUtils preferencesUtils;

    public boolean isRequestOrder () {
        return isRequestOrder;
    }

    public OrdersAPIService(Retrofit retrofit, PreferencesUtils preferencesUtils) {
        dataOrdersAPI = retrofit.create(IDataOrdersAPI.class);
        this.preferencesUtils = preferencesUtils;
    }

    public Observable<OrdersResponse> getOrders (Map<String,String> stringMap) {
        Map<String,String> headers = new HashMap<>();
        headers.put(Const.AUTHORIZATION,preferencesUtils.getToken());
        headers.put(Const.RETAILER,preferencesUtils.loadConfig().getKiotConfig().getRetailer());

        return dataOrdersAPI.REQUEST_ORDER_KIOT_VIET_CALL(headers,stringMap)
                .doOnSubscribe(disposable -> isRequestOrder = true)
                .doOnTerminate(() -> isRequestOrder = false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
