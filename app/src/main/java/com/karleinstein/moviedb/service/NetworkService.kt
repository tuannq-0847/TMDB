package com.karleinstein.moviedb.service

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.karleinstein.moviedb.data.remote.ApiService
import com.karleinstein.moviedb.utils.replaceItem
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale
import java.util.concurrent.TimeUnit

class NetworkService {
    fun createAppRetrofit(): ApiService {
        val client = OkHttpClient.Builder()
            .addInterceptor(StethoInterceptor())
            .readTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .connectTimeout(60L, TimeUnit.SECONDS)

        client.addInterceptor { chain ->
            val original = chain.request()
            val url = original.url().newBuilder()
                .addQueryParameter(KEY, API_KEY)
                .addQueryParameter(
                    LANGUAGE,
                    Locale.getDefault().toString().replaceItem()
                )
                .build()
            val request = original.newBuilder()
                .url(url)
                .build()
            return@addInterceptor chain.proceed(request)
        }
        return Retrofit.Builder()
            .client(client.build())
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    companion object {
        const val KEY = "api_key"
        const val LANGUAGE = "language"
        const val BASE_URL = "https://api.themoviedb.org/"
        const val API_KEY = "1f54bd990f1cdfb230adb312546d765d"
    }
}

