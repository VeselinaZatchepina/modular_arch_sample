package com.github.veselinazatchepina.repositories.datasource.net

import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object MovieRetrofit {

    private const val API_KEY_QUERY_KEY = "api_key"
    private const val API_KEY_QUERY_VALUE = "2029b9b9a4df571ae95c0e073b918fb9"
    private const val BASE_URL = "https://api.themoviedb.org/3/movie/"

    private val interceptor: Interceptor
    private val retrofit: Retrofit
    val movieApi: MovieApiInterface

    init {
        interceptor = Interceptor {

            val original = it.request()
            val httpUrl = original.url()

            val newHttpUrl = httpUrl
                .newBuilder()
                .addQueryParameter(API_KEY_QUERY_KEY, API_KEY_QUERY_VALUE)
                .build()

            val requestBuilder = original
                .newBuilder()
                .url(newHttpUrl)

            val request = requestBuilder
                .build()

            it.proceed(request)
        }
        val interceptorLogging = HttpLoggingInterceptor()
        interceptorLogging.level = HttpLoggingInterceptor.Level.HEADERS
        val okHttpBuilder = OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
        okHttpBuilder.addInterceptor(interceptorLogging)

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpBuilder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        movieApi = retrofit.create(
            MovieApiInterface::class.java)
    }

}