package vn.cns.covid19.login;

import android.content.Context;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import vn.cns.covid19.Utils.Const;
import vn.cns.covid19.Utils.PreferencesUtils;
import vn.cns.covid19.model.config.RequestDataConfigAPIService;
import vn.cns.covid19.model.token.RequestTokenAPIService;
import vn.cns.covid19.model.token.TokenResponse;
import vn.cns.covid19.services.APIUtils;

public class LoginRepository {
    private static LoginRepository instance;
    private PreferencesUtils preferencesUtils;
    private RequestTokenAPIService requestTokenAPIService;
    private RequestDataConfigAPIService requestDataConfigAPIService;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private LoginRepository (Context context) {
        this.preferencesUtils = PreferencesUtils.getInstance();
        APIUtils apiUtils = new APIUtils(Const.KIOT_HOST_ID);
        requestTokenAPIService = new RequestTokenAPIService(apiUtils.getService());

        apiUtils = new APIUtils(Const.CMS_HOST);
        requestDataConfigAPIService = new RequestDataConfigAPIService(apiUtils.getService());
    }

    public static LoginRepository getInstance(Context context) {
        synchronized (LoginRepository.class) {
            if (instance == null) {
                instance = new LoginRepository(context);
            }

            return instance;
        }
    }

    public MaybeSource<Object> login(String samId) {
        return requestDataConfigAPIService.getConfig(samId).flatMap(tokenResponse -> getToken());
    }

    private MaybeSource<TokenResponse> getToken () {
        return requestTokenAPIService.login();
    }
}
