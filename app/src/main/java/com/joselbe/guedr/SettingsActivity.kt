package com.joselbe.guedr

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RadioGroup

class SettingsActivity : AppCompatActivity() {

    companion object {
        val EXTRA_UNIT = "EXTRA_UNITS"

        //devuelve el intent. Pasamos el contexto y el tipo unidades por defecto
        fun intent(context : Context, units: Int) : Intent {
            val intent =  Intent(context, SettingsActivity::class.java)
            intent.putExtra(EXTRA_UNIT, units) //metemos el tipo de unidades
            return intent
        }
    }

    //var radioGroup : RadioGroup? = null

    val radioGroup by lazy {
        findViewById<RadioGroup>(R.id.units_rb)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        //Es una closure que recibe una v o incluso quitarlo  "v ->"
        findViewById<View>(R.id.btnaceptar).setOnClickListener { accepSettings()}
        findViewById<View>(R.id.btncancelar).setOnClickListener { cancelSettings() }
        //radioGroup = findViewById(R.id.units_rb)

        //seleciconamos el radio correcto que nos han pasado al crear el Intent
        val radioSelected = intent.getIntExtra(EXTRA_UNIT, R.id.celsius_rb)
        radioGroup?.check(radioSelected) //seleccionamos
    }
    private fun accepSettings(){
        val returnIntent = Intent()
        returnIntent.putExtra(EXTRA_UNIT, radioGroup?.checkedRadioButtonId)
        setResult(Activity.RESULT_OK, returnIntent) //le mandamos el intent con el valor
        //finalizamos esta actividad, regrsando anterior
        finish()
    }
    private fun cancelSettings(){
        setResult(Activity.RESULT_CANCELED)

        //finalizamos esta actividad, regrsando anterior
        finish()
    }
}
