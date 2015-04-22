package uk.andrewjack.android.bass;

import android.app.Service;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.support.v4.app.NotificationManagerCompat;
import android.view.inputmethod.InputMethodManager;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

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
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    Resources provideApplicationResources() {
        return application.getResources();
    }

    @Provides
    @Singleton
    ConnectivityManager provideConnectivityManager() {
        return (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Provides
    @Singleton
    InputMethodManager inputMethodManager() {
        return (InputMethodManager) application.getSystemService(Service.INPUT_METHOD_SERVICE);
    }

    @Provides
    @Singleton
    NotificationManagerCompat notificationManager() {
        return NotificationManagerCompat.from(application);
    }

    @Provides
    @Singleton
    OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    Picasso picasso(OkHttpClient okHttpClient) {
        return new Picasso.Builder(application)
                .downloader(new OkHttpDownloader(okHttpClient))
                .build();
    }

    @Provides
    @Singleton
    Bus bus() {
        return new Bus(ThreadEnforcer.ANY);
    }

}
