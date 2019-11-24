package br.geanezini.mobile.chuvanocaminho

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.geanezini.mobile.chuvanocaminho.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        // Permissões

//        if (!checkInternetConnection(this)) {
//            val dialogBuilder = AlertDialog.Builder(this)
//            dialogBuilder.setMessage("Não foi possível buscar as informações para a cidade informada. " +
//                        "Cheque sua conexão e tente novamente mais tarde.")
//                .setPositiveButton("OK") { it,_ -> it.cancel() }
//
//            val dialog = dialogBuilder.create()
//            dialog.setTitle("Sem conexão")
//            dialog.show()
//        } else {
//            // Abrir tela de detalhes da previsão do tempo
//        }
    }
}
