package com.threeforcom.testexam.di.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.threeforcom.testexam.data.rest.ApiServices
import com.threeforcom.testexam.utils.Constance
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Named("baseUrl")
    @Singleton
    internal fun provideBaseUrl(): String {
        return Constance.BASE_URL
    }

    @Provides
    @Named("gson")
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @Named("api")
    @Singleton
    internal fun provideOkhttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
        return client.build()
    }

    @Provides
    @Named("apiRetrofit")
    @Singleton
    internal fun provideRetrofit(
        @Named("baseUrl") baseUrl: String, @Named("gson") gson: Gson, @Named(
            "api"
        ) okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Named("googleRetrofit")
    @Singleton
    internal fun provideGoogleRetrofit(
        @Named("baseGoogleUrl") baseGoogleUrl: String, @Named("gson") gson: Gson, @Named(
            "api"
        ) okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(baseGoogleUrl)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideApiService(@Named("apiRetrofit") retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }
}
