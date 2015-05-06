package com.example.android.bass;

import android.app.Service;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.support.v4.app.NotificationManagerCompat;
import android.view.inputmethod.InputMethodManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BassAppModule {

    private final BassApp application;

    public BassAppModule(BassApp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideAppContext() {
        return application;
    }

    @Provides
    @Singleton
    Resources provideAppResources() {
        return application.getResources();
    }

    @Provides
    @Singleton
    ConnectivityManager provideConnectivityManager() {
        return (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Provides
    @Singleton
    InputMethodManager provideInputMethodManager() {
        return (InputMethodManager) application.getSystemService(Service.INPUT_METHOD_SERVICE);
    }

    @Provides
    @Singleton
    NotificationManagerCompat provideNotificationManager() {
        return NotificationManagerCompat.from(application);
    }

}
