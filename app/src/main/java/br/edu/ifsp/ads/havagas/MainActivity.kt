package br.edu.ifsp.ads.havagas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import br.edu.ifsp.ads.havagas.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val activityMainBinding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        val idTelefone: Int = activityMainBinding.tipoTelefoneRg.checkedRadioButtonId
        val idSexo: Int = activityMainBinding.sexoRg.checkedRadioButtonId
        var rbTelefoneSelecionado: RadioButton = activityMainBinding.tipoTelefoneRg.findViewById(idTelefone)
        var rbSexoSelecionado: RadioButton = activityMainBinding.sexoRg.findViewById(idSexo)

        with(activityMainBinding) {
            formacaoSp.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    if (position == 0 || position == 1) {
                        visibilidadeAnoFormaturaEt()

                    }
                    if (position == 2 || position == 3) {
                        visibilidadeGraduacaoOuMestrado()

                    }
                    if (position == 4 || position == 5) {
                        visibilidadeMestradoOuDoutorado()

                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
    }

    private fun visibilidadeMestradoOuDoutorado() {
        activityMainBinding.anoFormaturaEt.setText("")
        activityMainBinding.anoConclusaoEt.setText("")
        activityMainBinding.instituicaoEt.setText("")
        activityMainBinding.metradoOuDoutoradoLl.visibility = View.VISIBLE
    }

    private fun visibilidadeGraduacaoOuMestrado() {
        activityMainBinding.anoFormaturaEt.setText("")
        activityMainBinding.anoConclusaoMestEt.setText("")
        activityMainBinding.instituicaoMesEt.setText("")
        activityMainBinding.graducaoOuEspecializacaoLl.visibility = View.VISIBLE
    }

    private fun visibilidadeAnoFormaturaEt() {
        activityMainBinding.anoConclusaoMestEt.setText("")
        activityMainBinding.instituicaoMesEt.setText("")
        activityMainBinding.anoConclusaoEt.setText("")
        activityMainBinding.instituicaoEt.setText("")
        activityMainBinding.anoFormaturaEt.visibility = View.VISIBLE
    }
}