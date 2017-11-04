package com.joselbe.guedr

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class ForcastUnitTest {

    lateinit var forecast : Forecast

    @Before
    fun setUp(){
        //here prepare our test
        forecast = Forecast(25f,10f,35f,"Soleado con nubes", R.drawable.ico_01)
    }

    @Test
    fun maxTempUintConversion_isCorrect(){
        assertEquals(77f, forecast.getMaxTemp(Forecast.TempUnit.FAHRENHEIT))
    }
    @Test
    fun minTempUintConversion_isCorrect(){
        assertEquals(50f, forecast.getMinTemp(Forecast.TempUnit.FAHRENHEIT))
    }
    @Test
    fun maxTempUnits_inCelsius(){
        assertEquals(25f, forecast.getMaxTemp(Forecast.TempUnit.CELSIUS))
    }
    @Test
    fun minTempUnits_inCelsius(){
        assertEquals(10f, forecast.getMinTemp(Forecast.TempUnit.CELSIUS))
    }
    @Test(expected = IllegalArgumentException::class)
    fun humidityOverRabge_thowsArgumentsExceptcion(){
        Forecast(25f,10f,100.01f,"Descroption", R.drawable.ico_01)
    }
    @Test(expected = IllegalArgumentException::class)
    fun humidityUnderRabge_thowsArgumentsExceptcion(){
        Forecast(25f,10f,-1f,"Descroption", R.drawable.ico_01)
    }
}