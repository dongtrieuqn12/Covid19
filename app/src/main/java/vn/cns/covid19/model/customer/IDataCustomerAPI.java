package vn.cns.covid19.model.customer;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

public interface IDataCustomerAPI {

    @Headers("Content-Type: application/json")
    @GET("customers")
    Observable<CustomerResponse> GET_CUSTOMERS(@HeaderMap Map<String, String> headers, @QueryMap Map<String,String> queryMaps);
}
