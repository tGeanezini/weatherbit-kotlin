package br.geanezini.mobile.chuvanocaminho

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import br.geanezini.mobile.chuvanocaminho.databinding.FragmentForecastBinding
import br.geanezini.mobile.chuvanocaminho.infrastructure.models.CityForecast
import br.geanezini.mobile.chuvanocaminho.infrastructure.services.RetrofitInitializer
import kotlinx.android.synthetic.main.fragment_forecast.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentForecastBinding>(inflater,
            R.layout.fragment_forecast, container, false)
        val city = arguments!!.getString("city")
        val context = requireContext()
        val call = RetrofitInitializer(context).forecastService()
            .getForecast(city, API_KEY, SEARCH_LANG, SEARCH_DAYS)

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
