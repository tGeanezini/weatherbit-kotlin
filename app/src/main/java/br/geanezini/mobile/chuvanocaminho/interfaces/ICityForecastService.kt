package br.geanezini.mobile.chuvanocaminho.interfaces

import br.geanezini.mobile.chuvanocaminho.models.CityForecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ICityForecastService {
    @GET("forecast/daily")
    fun getForecast(@Query("city") city: String,
                    @Query("key") key: String,
                    @Query("lang") lang: String,
                    @Query("days") days: Int) : Call<CityForecast>
}