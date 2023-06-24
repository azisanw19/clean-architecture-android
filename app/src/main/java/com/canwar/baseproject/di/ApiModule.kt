package com.canwar.baseproject.di

import com.canwar.baseproject.remote.api.ApiServices
import com.canwar.baseproject.utils.API_KEY
import com.canwar.baseproject.utils.BASE_URL
import com.canwar.baseproject.utils.NETWORK_TIMEOUT
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideBaseUrl() = BASE_URL

    @Provides
    fun provideConnectionTimeout() = NETWORK_TIMEOUT

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val requestInterceptor = Interceptor { chain ->
            // Use this to api key in get
            val url = chain.request()
                .url
                .newBuilder()
//                .addQueryParameter("api_key", API_KEY)
                .build()

            // use this to header api key
            val request = chain.request()
                .newBuilder()
                .addHeader("API", API_KEY)
                .url(url)
                .build()
            return@Interceptor chain.proceed(request)
        }

        return OkHttpClient
            .Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideGson() : Gson = GsonBuilder()
        .serializeNulls()
        .setPrettyPrinting()
        .create()


    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String = provideBaseUrl(), gson: Gson = provideGson(), client: OkHttpClient = provideOkHttpClient()): ApiServices =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServices::class.java)

}