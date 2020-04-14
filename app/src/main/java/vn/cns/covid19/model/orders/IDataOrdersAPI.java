package vn.cns.covid19.model.orders;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

public interface IDataOrdersAPI {

    @Headers("Content-Type: application/json")
    @GET("orders")
    Observable<OrdersResponse> REQUEST_ORDER_KIOT_VIET_CALL(@HeaderMap Map<String, String> headers, @QueryMap Map<String,String> queryMaps);

}
