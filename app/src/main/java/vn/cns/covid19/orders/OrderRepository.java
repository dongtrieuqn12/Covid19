package vn.cns.covid19.orders;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import retrofit2.Retrofit;
import vn.cns.covid19.Utils.Const;
import vn.cns.covid19.Utils.PreferencesUtils;
import vn.cns.covid19.model.customer.CustomerAPIService;
import vn.cns.covid19.model.customer.CustomerResponse;
import vn.cns.covid19.model.orders.IDataOrdersAPI;
import vn.cns.covid19.model.orders.OrderData;
import vn.cns.covid19.model.orders.OrdersAPIService;
import vn.cns.covid19.model.orders.OrdersResponse;
import vn.cns.covid19.services.APIUtils;

public class OrderRepository {
    private static OrderRepository instance;
    private OrdersAPIService ordersAPIService;
    private PreferencesUtils preferencesUtils;
    private CustomerAPIService customerAPIService;

    private OrderRepository (Context context) {
        preferencesUtils = PreferencesUtils.getInstance();
        APIUtils apiUtils = new APIUtils(Const.KIOT_HOST);
        Retrofit retrofit = apiUtils.getService();
        ordersAPIService = new OrdersAPIService(retrofit, preferencesUtils);

        customerAPIService = new CustomerAPIService(retrofit,preferencesUtils);
    }

    public static OrderRepository getInstance(Context context) {
        synchronized (OrderRepository.class) {
            if (instance == null) {
                instance = new OrderRepository(context);
            }
            return instance;
        }
    }

    @SuppressLint("CheckResult")
    public Maybe<Map<String,Object>> getData (String customerCode) {
       return Maybe.zip(getOrders(customerCode), getCustomer(customerCode), this::processResult);
    }

    private Map<String,Object> processResult (OrdersResponse ordersResponse, CustomerResponse customerResponse) {
        Map<String,Object> map = new HashMap<>();
        map.put("orders",ordersResponse);
        map.put("customer",customerResponse);
        return map;
    }

    private Maybe<CustomerResponse> getCustomer (String customerCode) {
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("customerCode",customerCode);
        return customerAPIService.getCustomers(stringMap);
    }

    private Maybe<OrdersResponse> getOrders (String customerCode) {
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("customerCode",customerCode);
        stringMap.put("branchIds",preferencesUtils.loadConfig().getKiotConfig().getBranchId());
        stringMap.put("status","1");
        stringMap.put("pageSize","100");
        stringMap.put("orderBy","createdDate");
        stringMap.put("orderDirection","Desc");
        return ordersAPIService.getOrders(stringMap);
    }
}
