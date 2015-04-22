package com.example.android.bass;

import javax.inject.Singleton;

import dagger.Component;
import com.example.android.bass.data.api.ApiModule;

@Singleton
@Component(modules = {BassAppModule.class, ApiModule.class})
public interface BassComponent extends BassGraph {

    public final static class Initializer {
        public static BassComponent init(BassApp app) {
            return DaggerBassComponent.builder()
                    .bassAppModule(new BassAppModule(app))
                    .apiModule(new ApiModule(app.getResources()))
                    .build();
        }

        private Initializer() {
        } // No instances.
    }

}
