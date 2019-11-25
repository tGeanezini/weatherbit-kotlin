package br.geanezini.mobile.chuvanocaminho

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import br.geanezini.mobile.chuvanocaminho.databinding.FragmentForecastBinding
import br.geanezini.mobile.chuvanocaminho.infrastructure.models.CityForecast
import br.geanezini.mobile.chuvanocaminho.infrastructure.services.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentForecastBinding>(inflater, R.layout.fragment_forecast, container, false)

        val call = RetrofitInitializer(requireContext()).forecastService().getForecast("Curitiba", API_KEY, "pt", 2)

        call.enqueue(object: Callback<CityForecast> {
            override fun onResponse(call: Call<CityForecast>, response: Response<CityForecast>) {
                response?.body()?.let {
                    // Carrega a previsão do tempo para o dia seguinte
                    val forecast = it.forecastData.last()

                    binding.cityText.text = it.cityName
                    binding.temperatureText.text = forecast.averageTemperature.toString() + "° C"
                    binding.weatherDescText.text = forecast.weatherInfo.description
                    binding.rainChanceValueText.text = forecast.probabilityOfPrecipitation.toString() + "%"
                    binding.maxTempValueText.text = forecast.maxTemperature.toString() + "° C"
                    binding.minTempValueText.text = forecast.minimumTemperature.toString() + "° C"
                }
            }

            override fun onFailure(call: Call<CityForecast>, t: Throwable) {
                // Mostrar dialog de falha
            }
        })

        return binding.root
    }
}
