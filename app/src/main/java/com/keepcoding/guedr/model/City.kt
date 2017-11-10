package com.keepcoding.guedr.model

import java.io.Serializable

data class City(var name: String, var forecast: List<Forecast>?) : Serializable {
    override fun toString() = name

    constructor(name: String) : this(name, null)  //llamamos al otro consrrutor

}