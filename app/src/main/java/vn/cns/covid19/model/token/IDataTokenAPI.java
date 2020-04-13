package vn.cns.covid19.model.token;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IDataTokenAPI {
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("connect/token")
    @FormUrlEncoded
    Observable<TokenResponse> RETURN_ACCESSTOKEN_KIOTVIET(@FieldMap Map<String, String> headers);
}
