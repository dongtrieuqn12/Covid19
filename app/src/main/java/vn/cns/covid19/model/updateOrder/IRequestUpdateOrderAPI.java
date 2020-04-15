package vn.cns.covid19.model.updateOrder;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import vn.cns.covid19.model.orders.OrderData;

public interface IRequestUpdateOrderAPI {

    @Headers("Content-Type: application/json")
    @PUT("orders/{id}")
    Observable<OrderData> UPDATE_ORDER_EXECUTE(@HeaderMap Map<String, String> headers,
                                               @Path("id") int idOrder,
                                               @Body OrderUpdateRequest body);
}
