package z_aksys.solutions.digiappequitybb;


import android.content.Context;

import com.clevertap.android.sdk.Application;

public class App extends Application {

    private static Context mContext;

    private static Application instance;

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}