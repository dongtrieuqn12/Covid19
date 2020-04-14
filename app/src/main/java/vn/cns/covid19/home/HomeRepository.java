package vn.cns.covid19.home;

import android.content.Context;

public class HomeRepository {
    private static HomeRepository instance;

    private HomeRepository(Context context) {

    }

    public static HomeRepository getInstance(Context context) {
        synchronized (HomeRepository.class) {
            if (instance == null) {
                instance = new HomeRepository(context);
            }

            return instance;
        }
    }
}
