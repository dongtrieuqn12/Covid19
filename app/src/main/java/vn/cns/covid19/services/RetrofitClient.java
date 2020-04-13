package vn.cns.covid19.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final int SECOND = 60;
    static Retrofit getClient(String baseUrl){
        OkHttpClient builder = new OkHttpClient.Builder()
                .readTimeout(SECOND, TimeUnit.SECONDS)
                .writeTimeout(SECOND,TimeUnit.SECONDS)
                .connectTimeout(SECOND,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(builder)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
