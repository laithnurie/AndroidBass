package uk.andrewjack.android.bass;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import timber.log.Timber;

public class BassApp extends Application {

    private BassComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = BassComponent.Initializer.init(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

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

}
