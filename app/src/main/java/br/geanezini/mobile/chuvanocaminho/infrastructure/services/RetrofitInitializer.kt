package br.geanezini.mobile.chuvanocaminho.infrastructure.services

import android.content.Context
import br.geanezini.mobile.chuvanocaminho.infrastructure.interfaces.ICityForecastService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInitializer {
    private var retrofit: Retrofit
    private var context: Context
    private var okHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .callTimeout(1, TimeUnit.MINUTES)
        .build()

    constructor(contextParam: Context) {
        this.context = contextParam
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.weatherbit.io/v2.0")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun forecastService() = retrofit.create(ICityForecastService::class.java)
}