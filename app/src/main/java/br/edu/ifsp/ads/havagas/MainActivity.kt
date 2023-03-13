package br.edu.ifsp.ads.havagas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.core.view.isVisible
import br.edu.ifsp.ads.havagas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        val NOME = "NOME"
        val EMAIL = "EMAIL"
        val EMAILCK = "EMAILCK"
        val TIPO_TELEFONE = "TIPO_TELEFONE"
        val TELEFONE = "TELEFONE"
        val CELULARCB = "CELULARCB"
        val CELULAR = "CELULAR"
        val SEXORG = "SEXO"
        val DATANASC = "DATA_DE_NASCIMENTO"
        val ANO_FORMATURA = "ANO_FORMATURA"
        val ANO_CONCLUSAO = "ANO_CONCLUSAO"
        val ANO_CONCLUSA_MEST_DOUT = "ANO_CONCLUSAO_MEST_DOUT"
        val INSTITUICAO = "INSTITUICAO"
        val INSTITUICAO_MEST_DOUT = "INSTITUICAO_MEST_DOUT"
        val TITULO_MONOGRAFIA = "TITULO_MONOGRAFIA"
        val ORIENTADOR = "ORIENTADOR"
    }

    private val activityMainBinding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        with(activityMainBinding) {
            nomeCompletoEt.setText(savedInstanceState.getString(NOME))
            emailEt.setText(savedInstanceState.getString(EMAIL))
            checkBoxEmail.isChecked = savedInstanceState.getBoolean(EMAILCK)
            tipoTelefoneRg.check(savedInstanceState.getInt(TIPO_TELEFONE))
            telefoneEt.setText(savedInstanceState.getString(TELEFONE))
            celularCb.isChecked = savedInstanceState.getBoolean(CELULARCB)
            celularEt.setText(savedInstanceState.getString(CELULAR))
            sexoRg.check(savedInstanceState.getInt(SEXORG))
            datanascEt.setText(savedInstanceState.getString(DATANASC))
            anoFormaturaEt.setText(savedInstanceState.getString(ANO_FORMATURA))
            anoConclusaoEt.setText(savedInstanceState.getString(ANO_CONCLUSAO))
            instituicaoEt.setText(savedInstanceState.getString(INSTITUICAO))
            anoConclusaoMestEt.setText(savedInstanceState.getString(ANO_CONCLUSA_MEST_DOUT))
            instituicaoMesEt.setText(savedInstanceState.getString(INSTITUICAO_MEST_DOUT))
            tituloEt.setText(savedInstanceState.getString(TITULO_MONOGRAFIA))
            orientadorEt.setText(savedInstanceState.getString(ORIENTADOR))
        }
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
                anoFormaturaEt.setText("")
                formacaoSp.setSelection(0)
                datanascEt.setText("")
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        with(activityMainBinding) {
            outState.putString(NOME, nomeCompletoEt.text.toString())
            outState.putString(EMAIL, emailEt.text.toString())
            outState.putBoolean(EMAILCK, checkBoxEmail.isChecked)
            if (tipoTelefoneRg.checkedRadioButtonId != -1) {
                outState.putInt(TIPO_TELEFONE, findViewById<RadioButton>(tipoTelefoneRg.checkedRadioButtonId).id)
            }
            outState.putString(TELEFONE, telefoneEt.text.toString())
            outState.putBoolean(CELULARCB, checkBoxEmail.isChecked)
            outState.putString(CELULAR, celularEt.text.toString())
            if (sexoRg.checkedRadioButtonId != -1)
                outState.putInt(SEXORG, findViewById<RadioButton>(sexoRg.checkedRadioButtonId).id)
            outState.putString(DATANASC, datanascEt.text.toString())
            outState.putString(ANO_FORMATURA, anoFormaturaEt.text.toString())
            outState.putString(ANO_CONCLUSAO, anoConclusaoEt.text.toString())
            outState.putString(INSTITUICAO, instituicaoEt.text.toString())
            outState.putString(ANO_CONCLUSA_MEST_DOUT, anoConclusaoMestEt.text.toString())
            outState.putString(INSTITUICAO_MEST_DOUT, instituicaoMesEt.text.toString())
            outState.putString(TITULO_MONOGRAFIA, tituloEt.text.toString())
            outState.putString(ORIENTADOR, orientadorEt.text.toString())
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
            if (sexoRg.checkedRadioButtonId != -1)
                camposEntrada.add("Sexo: ${findViewById<RadioButton>(sexoRg.checkedRadioButtonId).text}")
            if (datanascEt.text.isNotEmpty())
                camposEntrada.add("Data de Nascimento: ${datanascEt.text}")
            if (anoFormaturaEt.isVisible) {
                if (anoFormaturaEt.text.isNotEmpty())
                    camposEntrada.add("Fundamental, Ano de Formatura: ${anoFormaturaEt.text}")
            }
            if (graducaoOuEspecializacaoLl.isVisible) {
                if (formacaoSp.selectedItem.toString() == "Graduação") {
                    if (anoConclusaoEt.text.isNotEmpty() && instituicaoEt.text.isNotEmpty()) {
                        camposEntrada.add(
                            "Graduação, Ano de Conclusão e Instituição: " +
                                    "${anoConclusaoEt.text} - ${instituicaoEt.text}"
                        )
                    }
                }
                if (formacaoSp.selectedItem.toString() == "Especialização") {
                    if (anoConclusaoEt.text.isNotEmpty() && instituicaoEt.text.isNotEmpty()) {
                        camposEntrada.add(
                            "Especialização, Ano de Conclusão e Instituição: " +
                                    "${anoConclusaoEt.text} - ${instituicaoEt.text}"
                        )
                    }
                }
            }
            if (metradoOuDoutoradoLl.isVisible) {
                if (formacaoSp.selectedItem.toString() == "Mestrado") {
                    if (anoConclusaoMestEt.text.isNotEmpty() && instituicaoMesEt.text.isNotEmpty() && tituloEt.text.isNotEmpty()
                        && orientadorEt.text.isNotEmpty()) {
                        camposEntrada.add(
                            "Mestrado, Ano de Conclusão, Instituição, Título Monografia e Orientador: " +
                                    "${anoConclusaoEt.text} - ${instituicaoEt.text} - ${tituloEt.text}" +
                                    " - ${orientadorEt.text}"
                        )
                    }
                }
                if (formacaoSp.selectedItem.toString() == "Doutorado") {
                    if (anoConclusaoMestEt.text.isNotEmpty() && instituicaoMesEt.text.isNotEmpty() && tituloEt.text.isNotEmpty()
                        && orientadorEt.text.isNotEmpty()) {
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