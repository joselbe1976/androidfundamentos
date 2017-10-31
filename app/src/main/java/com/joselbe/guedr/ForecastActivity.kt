package com.joselbe.guedr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ForecastActivity : AppCompatActivity()  {

    //TAG name for Logs
    val TAG = ForecastActivity::class.java.canonicalName

    var forecast : Forecast? = null
        set(value){
            //Capturamos referencia a los objetos de la vista
            val forecastImage =  findViewById<ImageView>(R.id.image_forcast)
            val decrip =  findViewById<TextView>(R.id.forcast_description)
            val max =  findViewById<TextView>(R.id.txt_maxTemp)
            val min =  findViewById<TextView>(R.id.txt_minTemp)
            val hum =  findViewById<TextView>(R.id.txt_Humidity)

            //actualizamos los valores
            if (value != null) {
                forecastImage.setImageResource(value.icon)
                decrip.text = value.description
                max.text = getString(R.string.max_temp_format, value.maxTemp) //get format in string
                min.text = getString(R.string.min_temp_format, value.minTemp)
                hum.text = getString(R.string.hum_format, value.humidy)
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        //Creamos el modelo
       forecast = Forecast(25f, 10f, 89f, "Soleado con alguna nube", R.drawable.ico_01)

    }



}
