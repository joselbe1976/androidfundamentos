package com.keepcoding.guedr.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.keepcoding.guedr.R
import com.keepcoding.guedr.model.Forecast

class DetailActivity : AppCompatActivity() {


    companion object {
        private val EXTRA_FORECAST = "EXTRA_FORECAST"
        fun intent(context : Context, forecast : Forecast) : Intent{
             val intent =  Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_FORECAST, forecast)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
}
