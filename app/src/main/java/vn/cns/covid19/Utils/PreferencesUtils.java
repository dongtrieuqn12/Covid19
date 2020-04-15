package vn.cns.covid19.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;

import vn.cns.covid19.model.config.ConfigResponse;
import vn.cns.covid19.model.token.TokenResponse;

public class PreferencesUtils {
    private static final String KEY_CONFIG_POS = "configPOS";
    private static final String KEY_TOKEN = "token";

    private static PreferencesUtils mInstance;
    private SharedPreferences mSharedPreferences;

    private PreferencesUtils (Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void initInstance(Context context) {
        if (mInstance == null) {
            mInstance = new PreferencesUtils(context);
        }
    }

    public static PreferencesUtils getInstance () {
        return mInstance;
    }

    public void saveToken (TokenResponse tokenResponse) {
        Gson gson = new Gson();
        String jsonStringConfig = (tokenResponse != null) ? gson.toJson(tokenResponse) : null;
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(KEY_TOKEN,jsonStringConfig);
        editor.apply();
    }

    public String getToken () {
        String jsonString = mSharedPreferences.getString(KEY_TOKEN,null);
        if (jsonString == null) {
            return null;
        }

        return "Bearer " + new Gson().fromJson(jsonString,TokenResponse.class).getAccessToken();
    }

    public void saveConfigPOS (ConfigResponse configPOS) {
        Gson gson = new Gson();
        String jsonStringConfig = (configPOS != null) ? gson.toJson(configPOS) : null;
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(KEY_CONFIG_POS,jsonStringConfig);
        editor.apply();
    }

    public ConfigResponse loadConfig() {
        String jsonString = mSharedPreferences.getString(KEY_CONFIG_POS,null);
        if(jsonString == null) {
            return null;
        }
        return new Gson().fromJson(jsonString,ConfigResponse.class);
    }
}
