package com.example.android.bass;

import javax.inject.Singleton;

import dagger.Component;

import com.example.android.bass.data.DataModule;
import com.example.android.bass.data.api.ApiModule;

@Singleton
@Component(modules = {BassAppModule.class, DataModule.class, ApiModule.class})
public interface BassComponent extends BassGraph {

    final class Initializer {
        public static BassComponent init(BassApp app) {
            return DaggerBassComponent.builder()
                    .bassAppModule(new BassAppModule(app))
                    .dataModule(new DataModule(app.getApplicationContext()))
                    .apiModule(new ApiModule(app.getResources()))
                    .build();
        }

        private Initializer() {
        } // No instances.
    }

}
