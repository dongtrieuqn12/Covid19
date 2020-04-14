package vn.cns.covid19.orders;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import retrofit2.Retrofit;
import vn.cns.covid19.Utils.Const;
import vn.cns.covid19.Utils.PreferencesUtils;
import vn.cns.covid19.model.orders.IDataOrdersAPI;
import vn.cns.covid19.model.orders.OrderData;
import vn.cns.covid19.model.orders.OrdersAPIService;
import vn.cns.covid19.model.orders.OrdersResponse;
import vn.cns.covid19.services.APIUtils;

public class OrderRepository {
    private static OrderRepository instance;
    private OrdersAPIService ordersAPIService;
    private PreferencesUtils preferencesUtils;
    private MutableLiveData<List<OrderData>> listMutableLiveData = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private OrderRepository (Context context) {
        preferencesUtils = PreferencesUtils.getInstance();
        APIUtils apiUtils = new APIUtils(Const.KIOT_HOST);
        Retrofit retrofit = apiUtils.getService();
        ordersAPIService = new OrdersAPIService(retrofit, preferencesUtils);
    }

    public static OrderRepository getInstance(Context context) {
        synchronized (OrderRepository.class) {
            if (instance == null) {
                instance = new OrderRepository(context);
            }
            return instance;
        }
    }

    public MutableLiveData<List<OrderData>> getListMutableLiveData () {
        return listMutableLiveData;
    }

    public void getOrders (String customerCode) {
        List<OrderData> orderDataList = new ArrayList<>();
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("customerCode",customerCode);
        stringMap.put("branchIds",preferencesUtils.loadConfig().getKiotConfig().getBranchId());
        stringMap.put("status","1");
        stringMap.put("pageSize","100");
        stringMap.put("orderBy","createdDate");
        stringMap.put("orderDirection","Desc");
        compositeDisposable.add(ordersAPIService.getOrders(stringMap)
                .flatMap((Function<OrdersResponse, Observable<OrderData>>) ordersResponse -> {
                    if (ordersResponse.getTotal() == 0) {
                        throw new Exception("No data");
                    }
                    return Observable.fromArray(ordersResponse.getData().toArray(new OrderData[0]));
                })
                .subscribeWith(new DisposableObserver<OrderData>() {
                    @Override
                    public void onNext(OrderData orderData) {
                        orderDataList.add(orderData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listMutableLiveData.postValue(null);
                    }

                    @Override
                    public void onComplete() {
                        listMutableLiveData.postValue(orderDataList);
                    }
                }));
    }

    public void clear () {
        compositeDisposable.clear();
    }
}
