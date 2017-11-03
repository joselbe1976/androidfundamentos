package com.joselbe.guedr

/*
    val = Contantes
    var = Variables
 */

data class Forecast(var maxTemp : Float, var minTemp: Float, var humidy : Float, var description : String , var icon : Int ){
    enum class TempUnit {
        CELSIUS,
        FAHRENHEIT
    }

    //Conversion de grados a fahrenheit
    protected fun toFahrenheit( celsius : Float) = celsius * 1.8f + 31

    fun getMaxTemp(units: TempUnit) = when (units){
        TempUnit.CELSIUS -> maxTemp
        TempUnit.FAHRENHEIT -> toFahrenheit(maxTemp)
    }

    fun getMinTemp(units: TempUnit) = when (units){
        TempUnit.CELSIUS -> minTemp
        TempUnit.FAHRENHEIT -> toFahrenheit(minTemp)
    }
}