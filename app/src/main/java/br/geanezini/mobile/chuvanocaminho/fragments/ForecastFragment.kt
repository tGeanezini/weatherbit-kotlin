package br.geanezini.mobile.chuvanocaminho.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import br.geanezini.mobile.chuvanocaminho.utils.API_KEY
import br.geanezini.mobile.chuvanocaminho.R
import br.geanezini.mobile.chuvanocaminho.utils.SEARCH_DAYS
import br.geanezini.mobile.chuvanocaminho.utils.SEARCH_LANG
import br.geanezini.mobile.chuvanocaminho.databinding.FragmentForecastBinding
import br.geanezini.mobile.chuvanocaminho.models.CityForecast
import br.geanezini.mobile.chuvanocaminho.services.RetrofitInitializer
import kotlinx.android.synthetic.main.fragment_forecast.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class ForecastFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentForecastBinding>(inflater,
            R.layout.fragment_forecast, container, false)
        val city = arguments!!.getString("city")
        val context = requireContext()
        val call = RetrofitInitializer(
            context
        ).forecastService()
            .getForecast(city,
                API_KEY,
                SEARCH_LANG,
                SEARCH_DAYS
            )

        call.enqueue(object: Callback<CityForecast> {
            override fun onResponse(call: Call<CityForecast>, response: Response<CityForecast>) {
                response?.body()?.let {
                    forecastLoading.visibility = View.GONE
                    forecastInfo.visibility = View.VISIBLE

                    // Carrega a previsão do tempo para o dia seguinte
                    val forecast = it.forecastData.last()

                    binding.cityText.text = it.cityName
                    binding.temperatureText.text = forecast.averageTemperature.toString() + "° C"
                    binding.weatherDescText.text = forecast.weatherInfo.description
                    binding.rainChanceValueText.text = forecast.probabilityOfPrecipitation.toString() + "%"
                    binding.maxTempValueText.text = forecast.maxTemperature.toString() + "° C"
                    binding.minTempValueText.text = forecast.minimumTemperature.toString() + "° C"
                    binding.windSpeedValueText.text = forecast.windSpeed.roundToInt().toString() + " Km/h"

                    val timestampFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                    timestampFormat.timeZone = TimeZone.getDefault()

                    val sunriseTime = Date(forecast.sunriseTimestamp * 1000)
                    val sunsetTime = Date(forecast.sunsetTimestamp * 1000)

                    binding.sunriseValueText.text = timestampFormat.format(sunriseTime)
                    binding.sunsetValueText.text = timestampFormat.format(sunsetTime)
                }
            }

            override fun onFailure(call: Call<CityForecast>, t: Throwable) {
                // Mostrar dialog de falha
                val dialogBuilder = AlertDialog.Builder(context)
                dialogBuilder.setMessage(getString(R.string.get_forecast_failed_message))
                    .setPositiveButton("OK") { dialog,_ ->
                        dialog.cancel()
                        fragmentManager?.popBackStack()
                    }

                val dialog = dialogBuilder.create()
                dialog.setTitle(getString(R.string.get_forecast_failed_title))
                dialog.show()
            }
        })

        return binding.root
    }
}
