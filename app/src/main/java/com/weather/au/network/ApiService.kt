package com.weather.au.network

import com.weather.au.model.Weather
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://dnu5embx6omws.cloudfront.net/"

private val interceptor = run {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.apply {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }
}

private val okHttpClient = OkHttpClient.Builder()
    .addNetworkInterceptor(interceptor) // same for .addInterceptor(...)
    .build()

// Using retrofit 2 to pull data from Network

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .client(okHttpClient)
    .build()

interface WeatherApiEndPoint{
    @GET("venues/weather.json")
    fun getWeatherInfo(): Single<Weather>
}

object WeatherApi{
    val retrofitService: WeatherApiEndPoint by lazy{
        retrofit.create(WeatherApiEndPoint::class.java)
    }
}