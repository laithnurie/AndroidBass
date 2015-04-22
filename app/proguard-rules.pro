# Retrofit, OkHttp, Gson
-keepattributes *Annotation*
-keepattributes Signature
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**
-dontwarn rx.**
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
-keep class sun.misc.Unsafe { *; }
-dontwarn java.nio.file.*
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Application classes that will be serialized/deserialized over Gson
-keep interface com.example.android.bass.data.api.FiveHundredPxApi { *; }
-keep class com.example.android.bass.data.api.FiveHundredPxApi { *; }
-keep class com.example.android.bass.data.model.FiveHundredPxSearchResult { *; }
-keep class com.example.android.bass.data.model.FiveHundredPxPhoto { *; }
-keep class com.example.android.bass.data.model.FiveHundredPxUser { *; }

# Butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# RxAndroid
-dontwarn rx.internal.util.**