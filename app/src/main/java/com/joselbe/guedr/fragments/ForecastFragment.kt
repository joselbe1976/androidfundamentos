package com.joselbe.guedr.fragments

import android.app.Activity
import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import com.joselbe.guedr.PREFERENCE_SHOW_CELSIUS
import com.joselbe.guedr.R
import com.joselbe.guedr.activity.SettingsActivity
import com.joselbe.guedr.models.Forecast


class ForecastFragment : Fragment() {

    //Es como un estatic de java
    companion object {
        val REQUEST_UNIT = 1
    }

    var max : TextView? = null
    var min : TextView? = null

    lateinit var root : View

    var forecast : Forecast? = null
        set(value){
            //cuando se establece el valor de forcast, le asigno el value, lo fuerzo.
            field = value

            //Capturamos referencia a los objetos de la vista
            val forecastImage =  root.findViewById<ImageView>(R.id.image_forcast)
            val decrip =  root.findViewById<TextView>(R.id.forcast_description)
            max =  root.findViewById<TextView>(R.id.txt_maxTemp)
            min =  root.findViewById<TextView>(R.id.txt_minTemp)
            val hum =  root.findViewById<TextView>(R.id.txt_Humidity)

            //actualizamos los valores
            value?.let {
                forecastImage.setImageResource(value.icon)
                decrip.text = value.description
                updateTemperature()
                hum.text = getString(R.string.hum_format, value.humidy)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)

        //creamos la vista a partir del inflater (si no viene null)
        inflater?.let{
            //it es el objeto inflatter en not null
            root  = it.inflate(R.layout.fragment_forecast, container, false)

            //Creamos el modelo
            forecast = Forecast(25f, 10f, 89f, "Soleado con alguna nube", R.drawable.ico_01)


        }
        return root

    }

    private fun updateTemperature() {
        val units = temperatureUnits()
        val unitsString = temperatureUnitsString(units)
        val maxTempString = getString(R.string.max_temp_format, forecast?.getMaxTemp(units), unitsString)
        val minTempString = getString(R.string.min_temp_format, forecast?.getMinTemp(units), unitsString)
        max?.text = maxTempString
        min?.text = minTempString

    }


    private fun temperatureUnitsString(units: Forecast.TempUnit) = when (units) {
        Forecast.TempUnit.CELSIUS -> "ºC"
        else -> "F"
    }

    private fun temperatureUnits() = if (PreferenceManager.getDefaultSharedPreferences(activity)
            .getBoolean(PREFERENCE_SHOW_CELSIUS, true)) {
        Forecast.TempUnit.CELSIUS
    }
    else {
        Forecast.TempUnit.FAHRENHEIT
    }




    // Menus
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.settings, menu) //inflamos y decimos que ebnganche en menu
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == R.id.menu_show_settings){
            //Creo el intent
            val units =  if (temperatureUnits() == Forecast.TempUnit.CELSIUS)
                R.id.celsius_rb
            else
                R.id.faranhait_rb

            val intent = SettingsActivity.intent(activity, units) //pedimos el intent al estatico que lo crea y le pasamos el tipo de units
            startActivityForResult(intent, REQUEST_UNIT)

            return true //ok es mi opcion de menu
        }
        return super.onOptionsItemSelected(item)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_UNIT){
            if (resultCode == Activity.RESULT_OK){
                val unitSelected = data?.getIntExtra(SettingsActivity.EXTRA_UNIT, R.id.celsius_rb)

                when (unitSelected) {
                    R.id.celsius_rb -> {
                        Log.v("TAG", "Han pulsado Grados celsius")
                        //Toast.makeText(this, "seleccionado Celsisus", Toast.LENGTH_LONG).show()
                    }
                    R.id.faranhait_rb -> {
                        Log.v("TAG", "Han pulsado Grados Faranheit")
                        //Toast.makeText(this, "seleccionado Faranheit", Toast.LENGTH_LONG).show()
                    }
                }

                // Guardamos la preferencia.
                val oldShowCelsius = temperatureUnits()

                PreferenceManager.getDefaultSharedPreferences(activity)
                        .edit()
                        .putBoolean(PREFERENCE_SHOW_CELSIUS, unitSelected == R.id.celsius_rb) //grabamos resttado comparacion
                        .apply()


                //actualizamos la temperatura
                updateTemperature()

                Snackbar.make(root, "Han cambiado las preferencias", Snackbar.LENGTH_LONG)
                        .setAction("Deshacer"){
                            //esto es una closure
                            PreferenceManager.getDefaultSharedPreferences(activity)
                                    .edit()
                                    .putBoolean(PREFERENCE_SHOW_CELSIUS, oldShowCelsius == Forecast.TempUnit.CELSIUS)
                                    .apply()

                            //actualizamos la temperatura
                            updateTemperature()
                        }
                        .show()
            }
            else if (resultCode == Activity.RESULT_CANCELED ){
                Log.v("TAG","Han pulsado Cancel")
            }
        }
    }
}