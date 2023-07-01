package com.canwar.baseproject.di

import com.canwar.baseproject.BuildConfig
import com.canwar.baseproject.remote.api.ApiServices
import com.canwar.baseproject.utils.NETWORK_TIMEOUT
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideRequestInterceptor() = Interceptor { chain ->
        // Use this to api key in get
        val url = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("key", BuildConfig.API_KEY)
            .build()

        // use this to header api key
        val request = chain.request()
            .newBuilder()
            .addHeader("Accept", "application/json")
//                .addHeader("key", API_KEY)
            .url(url)
            .build()
        return@Interceptor chain.proceed(request)
    }

    @Provides
    fun provideOkHttpClient(requestInterceptor: Interceptor) = if (BuildConfig.DEBUG) {
        /* Enable logging in build debug */

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        OkHttpClient.Builder()
            .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(requestInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        /* Disable Logging for Release */

        OkHttpClient.Builder()
            .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(requestInterceptor)
            .build()
    }

    @Provides
    fun provideGson() = GsonBuilder()
        .serializeNulls()
        .setPrettyPrinting()
        .setLenient()
        .create()


    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient)=
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServices::class.java)

}