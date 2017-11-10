package com.keepcoding.guedr.adapters

import android.preference.PreferenceManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.keepcoding.guedr.PREFERENCE_SHOW_CELSIUS
import com.keepcoding.guedr.R
import com.keepcoding.guedr.model.Forecast
import kotlinx.android.synthetic.main.content_forecast.view.*


class forecastRecyclerViewAdapter(val forecast : List<Forecast>, val tempUnits : Forecast.TempUnit) : RecyclerView.Adapter<forecastRecyclerViewAdapter.ForecastViewHolder>() {
   var onClickListener : View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.content_forecast, parent, false)
        view.setOnClickListener { onClickListener }

        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder?, position: Int) {
        holder?.bindForecast(forecast[position], tempUnits, position)
    }

    override fun getItemCount() = forecast.size


    inner class ForecastViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {

        // Acceso a los elementos de la vista
        val day = itemView.findViewById<TextView>(R.id.day)
        val forecastImage = itemView.findViewById<ImageView>(R.id.forecast_image)
        val maxTemp = itemView.findViewById<TextView>(R.id.max_temp)
        val minTemp = itemView.findViewById<TextView>(R.id.min_temp)
        val humidity = itemView.findViewById<TextView>(R.id.humidity)
        val description = itemView.findViewById<TextView>(R.id.forecast_description)

        fun bindForecast(forecast : Forecast, tempUnits : Forecast.TempUnit, position : Int){
            //Actualizamos lista con el modelo


            val context = forecastImage.context //asi es un truco de acceso al contexto

            forecastImage.setImageResource(forecast.icon)
            description.text = forecast.description
            humidity.text = context.getString(R.string.humidity_format, forecast.humidity)
            day.text = generateDayText(position)
            updateTemperature(forecast,tempUnits)

        }

        private fun generateDayText(position: Int) =
            when(position){
                0 -> "Hoy"
                1 -> "Mañana"
                2 -> "Pasado mañana"
                3 -> "Pasado Pasado mañana"
                4 -> "Pasado Pasado mañana"
                5 -> "Pasado Pasado  Pasadomañana"
                6 -> "Pasado Pasado Pasado Pasado mañana"
                else -> "Yo que se"
            }


        private fun updateTemperature(forecast : Forecast, units: Forecast.TempUnit) {
            val context = forecastImage.context
            val unitsString = temperatureUnitsString(units)
            val maxTempString = context.getString(R.string.max_temp_format, forecast.getMaxTemp(units), unitsString)
            val minTempString = context.getString(R.string.min_temp_format, forecast.getMinTemp(units), unitsString)
            maxTemp.text = maxTempString
            minTemp.text = minTempString
        }

        private fun temperatureUnitsString(units: Forecast.TempUnit) = when (units) {
            Forecast.TempUnit.CELSIUS -> "ºC"
            else -> "F"
        }


    }



}