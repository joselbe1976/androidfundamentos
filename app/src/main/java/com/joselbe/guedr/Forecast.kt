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


}