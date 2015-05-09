package com.example.android.bass;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.android.bass.data.StethoUtil;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import timber.log.Timber;

public class BassApp extends Application {

    private BassComponent component;
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            StethoUtil.setupStetho(this);
        }

        // Initialize dagger
        component = BassComponent.Initializer.init(this);

        SharedPreferences userPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        // TODO: use to set opt out setting on startup
        // userPrefs.getBoolean(getString(R.string.key_analytics), true);

        userPrefs.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals(getString(R.string.key_analytics))) {
                    // TODO: opt out of analytics
                }
            }
        });
    }

    public static BassComponent get(Context context) {
        return ((BassApp) context.getApplicationContext()).component;
    }

    public static RefWatcher getRefWatcher(Context context) {
        BassApp application = (BassApp) context.getApplicationContext();
        return application.refWatcher;
    }

}
