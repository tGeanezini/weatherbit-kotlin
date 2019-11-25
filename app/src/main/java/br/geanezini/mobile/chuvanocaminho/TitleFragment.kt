package br.geanezini.mobile.chuvanocaminho

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import br.geanezini.mobile.chuvanocaminho.databinding.FragmentTitleBinding
import kotlinx.android.synthetic.main.fragment_title.*

class TitleFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater,
            R.layout.fragment_title, container, false)
        val context = requireContext()

        binding.searchButton.setOnClickListener {
            if (!checkInternetConnection(context)) {
                val dialogBuilder = AlertDialog.Builder(context)
                dialogBuilder.setMessage(getString(R.string.check_connection_message))
                    .setPositiveButton("OK") { dialog,_ -> dialog.cancel() }

                val dialog = dialogBuilder.create()
                dialog.setTitle(getString(R.string.check_connection_title))
                dialog.show()
            } else if (searchCityText.text.isNullOrEmpty()) {
                val dialogBuilder = AlertDialog.Builder(context)
                dialogBuilder.setMessage(getString(R.string.search_city_alert_message))
                    .setPositiveButton("OK") { dialog,_ -> dialog.cancel() }

                val dialog = dialogBuilder.create()
                dialog.setTitle(getString(R.string.search_city_alert_title))
                dialog.show()
            } else {
                val city = searchCityText.text.toString()
                val bundle = bundleOf("city" to city)
                findNavController().navigate(R.id.action_titleFragment_to_forecastFragment, bundle)
            }
        }

        return binding.root
    }

    private fun checkInternetConnection(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }
}
