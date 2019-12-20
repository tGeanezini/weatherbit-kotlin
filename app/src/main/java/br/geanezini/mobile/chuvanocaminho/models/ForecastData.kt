package br.geanezini.mobile.chuvanocaminho.models

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

data class ForecastData (@SerializedName("moonrise_ts") var moonriseTimestamp: Long,
                         @SerializedName("wind_cdir") var windDirection: String,
                         @SerializedName("rh") var relativeHumidity: Int,
                         @SerializedName("pres") var averagePressure: String,
                         @SerializedName("high_temp") var higherTemperature: Double,
                         @SerializedName("sunset_ts") var sunsetTimestamp: Long,
                         @SerializedName("ozone") var ozoneAverage: Double,
                         @SerializedName("moon_phase") var moonPhase: Double,
                         @SerializedName("wind_gust_spd") var windGustSpeed: Double,
                         @SerializedName("snow_depth") var snowDepth: Double,
                         @SerializedName("clouds") var cloudCoverage: Int,
                         @SerializedName("ts") var forecastStartTimestamp: Long,
                         @SerializedName("sunrise_ts") var sunriseTimestamp: Long,
                         @SerializedName("app_min_temp") var feelsLikeMin: Double,
                         @SerializedName("wind_spd") var windSpeed: Double,
                         @SerializedName("pop") var probabilityOfPrecipitation: Int,
                         @SerializedName("wind_cdir_full") var verbalWindDirection: String,
                         @SerializedName("slp") var seaLevelPressure: Double,
                         @SerializedName("valid_date") var forecastValidDate: String,
                         @SerializedName("app_max_temp") var feelsLikeMax: Double,
                         @SerializedName("vis") var visibility: Double,
                         @SerializedName("dewpt") var dewPoint: Double,
                         @SerializedName("snow") var accumulatedSnowfall: Double,
                         @SerializedName("uv") var maximumUvIndex: Double,
                         @SerializedName("wind_dir") var windDirectionValue: Int,
                         @SerializedName("clouds_hi") var highLevelCloudCoverage: Int,
                         @SerializedName("precip") var accumulatedPrecipitation: Double,
                         @SerializedName("low_temp") var lowTemperature: Double,
                         @SerializedName("max_temp") var maxTemperature: Double,
                         @SerializedName("moonset_ts") var moonsetTimestamp: Long,
                         @SerializedName("temp") var averageTemperature: Double,
                         @SerializedName("min_temp") var minimumTemperature: Double,
                         @SerializedName("clouds_mid") var midLevelCloudCoverage: Int,
                         @SerializedName("clouds_low") var lowLevelCloudCoverage: Int,
                         @SerializedName("weather") var weatherInfo: WeatherInfo) {
    fun formatDoubleToInt(value: Double) : Int {
        return value.roundToInt()
    }

    fun tsToTime(ts: Long) : String {
        val timestampFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        timestampFormat.timeZone = TimeZone.getDefault()

        val tsTime = Date(ts * 1000)
        return timestampFormat.format(tsTime)
    }
}