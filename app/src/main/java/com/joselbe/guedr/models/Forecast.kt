package com.joselbe.guedr.models

/*
    val = Contantes
    var = Variables
 */

data class Forecast(var maxTemp : Float, var minTemp: Float, var humidy : Float, var description : String , var icon : Int ){
    enum class TempUnit {
        CELSIUS,
        FAHRENHEIT
    }

    //constructor por defecto, de arriba. Podeos añadir validaciones
    init {

        /*
        if (humidy < 0 || humidy > 100){
             throw  IllegalArgumentException("Humedad incorrecta")
         }
         */

        if (humidy !in 0f..100f){
            throw  IllegalArgumentException("Humedad incorrecta")
        }
    }

    //Conversion de grados a fahrenheit
    protected fun toFahrenheit( celsius : Float) = celsius * 1.8f + 32

    fun getMaxTemp(units: TempUnit) = when (units){
        TempUnit.CELSIUS -> maxTemp
        TempUnit.FAHRENHEIT -> toFahrenheit(maxTemp)
    }

    fun getMinTemp(units: TempUnit) = when (units){
        TempUnit.CELSIUS -> minTemp
        TempUnit.FAHRENHEIT -> toFahrenheit(minTemp)
    }
}