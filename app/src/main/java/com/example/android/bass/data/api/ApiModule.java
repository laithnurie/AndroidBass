package com.example.android.bass.data.api;

import android.content.res.Resources;

import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import com.example.android.bass.BuildConfig;
import com.example.android.bass.R;

@Module
public class ApiModule {

    private final Resources resources;

    public ApiModule(Resources resources) {
        this.resources = resources;
    }

    @Provides
    @Singleton
    FiveHundredPxApi fiveHundredPxApi(OkHttpClient okHttpClient) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setClient(new OkClient(okHttpClient))
                .setEndpoint(resources.getString(R.string.five_hundred_px_endpoint))
                .setLogLevel(BuildConfig.DEBUG ?
                        RestAdapter.LogLevel.FULL :
                        RestAdapter.LogLevel.NONE)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addQueryParam("consumer_key", resources.getString(R.string.consumer_key));
                    }
                })
                .build();
        return restAdapter.create(FiveHundredPxApi.class);
    }

}
