package br.edu.ifsp.ads.havagas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.core.view.get
import androidx.core.view.isVisible
import br.edu.ifsp.ads.havagas.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val activityMainBinding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)


        with(activityMainBinding) {
            celularCb.setOnClickListener {
                if (celularCb.isChecked)
                    celularEt.visibility = View.VISIBLE
                else
                    celularEt.visibility = View.GONE
            }
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

            btnSalvar.setOnClickListener {
                val dados : String = formatarDados(activityMainBinding)
                Toast.makeText(this@MainActivity, dados, Toast.LENGTH_SHORT).show()
            }

            btnLimpar.setOnClickListener {
                nomeCompletoEt.setText("")
                emailEt.setText("")
                checkBoxEmail.isChecked = false
                tipoTelefoneRg.clearCheck()
                telefoneEt.setText("")
                celularEt.setText("")
                celularCb.isChecked = false
                sexoRg.clearCheck()
                vagasInteresseEt.setText("")
                formacaoSp.setSelection(0)
            }
        }
    }

    private fun formatarDados(binding: ActivityMainBinding): String {
        var dadosCompletos : String
        val camposEntrada : MutableList<String> = mutableListOf()

        with(binding) {
            if (nomeCompletoEt.text.isNotEmpty())
                camposEntrada.add("Nome: ${nomeCompletoEt.text}")
            if (emailEt.text.isNotEmpty())
                camposEntrada.add("Email: ${emailEt.text}")
            if (checkBoxEmail.isChecked)
                camposEntrada.add("Deseja receber e-mails com atualizações: Sim")
            if (telefoneEt.text.isNotEmpty())
                camposEntrada.add("Telefone: ${telefoneEt.text}")
            if (tipoTelefoneRg.checkedRadioButtonId != -1)
                camposEntrada.add("Telefone ${findViewById<RadioButton>(tipoTelefoneRg.checkedRadioButtonId).text}")
            if (celularEt.isVisible)
                camposEntrada.add("Celular: ${celularEt.text}")
            camposEntrada.add("Sexo: ${findViewById<RadioButton>(sexoRg.checkedRadioButtonId).text}")
            if (datanascEt.text.isNotEmpty())
                camposEntrada.add("Data de Nascimento: ${datanascEt.text}")
            if (anoFormaturaEt.isVisible) {
                if (anoFormaturaEt.text.isNotEmpty())
                    camposEntrada.add("Fundamental, Ano de Formatura: ${anoFormaturaEt.text}")
            }
            if (graducaoOuEspecializacaoLl.isVisible) {
                if (formacaoSp.get(0).toString() == "Graduação") {
                    if (anoFormaturaEt.text.isNotEmpty() && instituicaoEt.text.isNotEmpty()) {
                        camposEntrada.add(
                            "Graduação, Ano de Conclusão e Instituição: " +
                                    "${anoConclusaoEt.text} - ${instituicaoEt.text}"
                        )
                    }
                }
                if (formacaoSp.get(0).toString() == "Especialização") {
                    if (anoFormaturaEt.text.isNotEmpty() && instituicaoEt.text.isNotEmpty()) {
                        camposEntrada.add(
                            "Especialização, Ano de Conclusão e Instituição: " +
                                    "${anoConclusaoEt.text} - ${instituicaoEt.text}"
                        )
                    }
                }
            }
            if (metradoOuDoutoradoLl.isVisible) {
                if (anoConclusaoMestEt.text.isNotEmpty() && instituicaoMesEt.text.isNotEmpty() && tituloEt.text.isNotEmpty()
                    && orientadorEt.text.isNotEmpty()) {
                    if (formacaoSp.get(0).toString() == "Mestrado") {
                        camposEntrada.add(
                            "Mestrado, Ano de Conclusão, Instituição, Título Monografia e Orientador: " +
                                    "${anoConclusaoEt.text} - ${instituicaoEt.text} - ${tituloEt.text}" +
                                    " - ${orientadorEt.text}"
                        )
                    }
                    if (formacaoSp.get(0).toString() == "Doutorado") {
                        camposEntrada.add(
                            "Doutorado, Ano de Conclusão, Instituição, Título Monografia e Orientador: " +
                                    "${anoConclusaoEt.text} - ${instituicaoEt.text} - ${tituloEt.text}" +
                                    " - ${orientadorEt.text}"
                        )
                    }
                }
            }
            if (vagasInteresseEt.text.isNotEmpty())
                camposEntrada.add("Vagas de Interesse: ${vagasInteresseEt.text}")
            dadosCompletos = camposEntrada.toString()
        }
        return dadosCompletos
    }

    private fun visibilidadeMestradoOuDoutorado() {
        activityMainBinding.anoFormaturaEt.setText("")
        activityMainBinding.anoConclusaoEt.setText("")
        activityMainBinding.instituicaoEt.setText("")
        activityMainBinding.metradoOuDoutoradoLl.visibility = View.VISIBLE
        activityMainBinding.graducaoOuEspecializacaoLl.visibility = View.GONE
        activityMainBinding.anoFormaturaEt.visibility = View.GONE
    }

    private fun visibilidadeGraduacaoOuMestrado() {
        activityMainBinding.anoFormaturaEt.setText("")
        activityMainBinding.anoConclusaoMestEt.setText("")
        activityMainBinding.instituicaoMesEt.setText("")
        activityMainBinding.graducaoOuEspecializacaoLl.visibility = View.VISIBLE
        activityMainBinding.anoFormaturaEt.visibility = View.GONE
        activityMainBinding.metradoOuDoutoradoLl.visibility = View.GONE
    }

    private fun visibilidadeAnoFormaturaEt() {
        activityMainBinding.anoConclusaoMestEt.setText("")
        activityMainBinding.instituicaoMesEt.setText("")
        activityMainBinding.anoConclusaoEt.setText("")
        activityMainBinding.instituicaoEt.setText("")
        activityMainBinding.anoFormaturaEt.visibility = View.VISIBLE
        activityMainBinding.metradoOuDoutoradoLl.visibility = View.GONE
        activityMainBinding.graducaoOuEspecializacaoLl.visibility = View.GONE
    }
}