package com.keepcoding.guedr.model

import com.keepcoding.guedr.R
import java.io.Serializable

//clase en singleton con object
//class Cities : Serializable {
object Cities : Serializable {
    private var cities: List<City> = listOf(
            City("Madrid"),
            City("Jaén"),
            City("Quito"),
            City("Alovera")
    )

    val count
        get() = cities.size

//    fun getCity(index: Int) = cities[index]
    operator fun get(i: Int) = cities[i]

    fun toArray() = cities.toTypedArray()
}