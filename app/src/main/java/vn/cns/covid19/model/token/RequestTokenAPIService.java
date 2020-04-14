package vn.cns.covid19.model.token;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import vn.cns.covid19.Utils.Const;
import vn.cns.covid19.Utils.PreferencesUtils;
import vn.cns.covid19.exception.LoginFailureException;
import vn.cns.covid19.model.config.ConfigResponse;

public class RequestTokenAPIService {
    private IDataTokenAPI dataTokenAPI;
    private boolean isRequestToken;

    public RequestTokenAPIService (Retrofit retrofit) {
        this.dataTokenAPI = retrofit.create(IDataTokenAPI.class);
    }

    public boolean isRequestToken() {
        return isRequestToken;
    }

    public Maybe<TokenResponse> login(ConfigResponse configResponse) {
        Map<String, String> headers = new HashMap<>();
        headers.put("client_id", configResponse.getKiotConfig().getClientId());
        headers.put("client_secret",configResponse.getKiotConfig().getClientSecret());
        headers.put("grant_type","client_credentials");
        headers.put("scopes","PublicApi.Access");

        return dataTokenAPI.RETURN_ACCESSTOKEN_KIOTVIET(headers)
                .doOnSubscribe(disposable -> isRequestToken = true)
                .doOnTerminate(() -> isRequestToken = false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(this::handleLoginError)
                .doOnNext(this::processTokenResponse)
                .singleElement();
    }

    private Observable<TokenResponse> handleLoginError(Throwable throwable) {
        throw new LoginFailureException();
    }

    private void processTokenResponse (TokenResponse tokenResponse) {
        PreferencesUtils.getInstance().saveToken(tokenResponse);
    }
}
