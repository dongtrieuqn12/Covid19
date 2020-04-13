package vn.cns.covid19.model.config;

import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import vn.cns.covid19.Utils.Const;
import vn.cns.covid19.Utils.PreferencesUtils;

public class RequestDataConfigAPIService {
    private IDataConfigAPI dataConfigAPI;
    private boolean isRequestConfig;

    public RequestDataConfigAPIService (Retrofit retrofit) {
        dataConfigAPI = retrofit.create(IDataConfigAPI.class);
    }

    public boolean isRequestConfig () {
        return isRequestConfig;
    }

    public Maybe<ConfigResponse> getConfig (String samId) {
        return dataConfigAPI.CONFIG_POS_CALL(Const.CMS_TKN,samId)
                .doOnSubscribe(disposable -> isRequestConfig = true)
                .doOnTerminate(() -> isRequestConfig = false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::saveConfig)
                .singleElement();
    }

    private void saveConfig(ConfigResponse configResponse) {
        PreferencesUtils.getInstance().saveConfigPOS(configResponse);
    }
}
