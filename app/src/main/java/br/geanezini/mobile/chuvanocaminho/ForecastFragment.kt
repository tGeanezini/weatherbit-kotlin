package br.geanezini.mobile.chuvanocaminho

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import br.geanezini.mobile.chuvanocaminho.databinding.FragmentForecastBinding

class ForecastFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var binding = DataBindingUtil.inflate<FragmentForecastBinding>(inflater, R.layout.fragment_forecast, container, false)
        return binding.root
    }
}
