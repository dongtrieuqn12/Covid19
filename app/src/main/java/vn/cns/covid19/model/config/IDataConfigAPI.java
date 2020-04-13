package vn.cns.covid19.model.config;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface IDataConfigAPI {
    @Headers("Content-Type: application/json")
    @GET("api/terminalInfo")
    Observable<ConfigResponse> CONFIG_POS_CALL(@Header("Authorization") String MCP_AT, @Query("samId") String samId);
}
