package vn.cns.covid19.services;

import retrofit2.Retrofit;

public class APIUtils {
    private String baseUrl;

    public APIUtils(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Retrofit getService() {
        return RetrofitClient.getClient(baseUrl);
    }
}
