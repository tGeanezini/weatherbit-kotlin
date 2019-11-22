package br.geanezini.mobile.chuvanocaminho.infrastructure.models

import com.google.gson.annotations.SerializedName

data class CityForecast (@SerializedName("city_name") var cityName: String,
                         @SerializedName("lon") var longitude: String,
                         @SerializedName("timezone") var timezone: String,
                         @SerializedName("lat") var latitude: String,
                         @SerializedName("country_code") var countryCode: String,
                         @SerializedName("state_code") var stateCode: String,
                         @SerializedName("data") var forecastData: ArrayList<ForecastData>)