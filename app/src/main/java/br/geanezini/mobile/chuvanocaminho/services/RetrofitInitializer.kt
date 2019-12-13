package br.geanezini.mobile.chuvanocaminho.services

import android.content.Context
import br.geanezini.mobile.chuvanocaminho.interfaces.ICityForecastService
import br.geanezini.mobile.chuvanocaminho.utils.BASE_URL
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
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun forecastService() = retrofit.create(ICityForecastService::class.java)
}