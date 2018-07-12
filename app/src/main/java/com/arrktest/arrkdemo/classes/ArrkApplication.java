package com.arrktest.arrkdemo.classes;

import android.app.Application;
import android.util.Log;

import com.facebook.stetho.Stetho;

import io.reactivex.plugins.RxJavaPlugins;

public class ArrkApplication extends Application {

    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

        netComponent = DaggerNetComponent.builder()
                .netModule(new NetModule(AppConstants.DOMAIN))
                .appModule(new AppModule(this))
                .build();

        RxJavaPlugins.setErrorHandler(throwable -> Log.e("ArrkApplication", throwable.getMessage()));

    }

    public NetComponent getNetComponent() {
        return netComponent;
    }
}
