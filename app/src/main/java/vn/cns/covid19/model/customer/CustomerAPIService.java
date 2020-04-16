package vn.cns.covid19.model.customer;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Maybe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import vn.cns.covid19.Utils.Const;
import vn.cns.covid19.Utils.PreferencesUtils;

public class CustomerAPIService {
    private IDataCustomerAPI dataCustomerAPI;
    private boolean isRequestCustomer;
    private PreferencesUtils preferencesUtils;

    public boolean isRequestCustomer() {
        return isRequestCustomer;
    }

    public CustomerAPIService(Retrofit retrofit, PreferencesUtils preferencesUtils) {
        dataCustomerAPI = retrofit.create(IDataCustomerAPI.class);
        this.preferencesUtils = preferencesUtils;
    }

    public Maybe<CustomerResponse> getCustomers (Map<String,String> queryMap) {
        Map<String,String> headerMap = new HashMap<>();
        headerMap.put(Const.AUTHORIZATION, preferencesUtils.getToken());
        headerMap.put(Const.RETAILER,preferencesUtils.loadConfig().getKiotConfig().getRetailer());
        return dataCustomerAPI.GET_CUSTOMERS(headerMap,queryMap)
                .doOnSubscribe(disposable -> isRequestCustomer = true)
                .doOnTerminate(() -> isRequestCustomer = false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .singleElement();
    }
}
