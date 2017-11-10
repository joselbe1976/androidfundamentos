package com.keepcoding.guedr.activity

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.keepcoding.guedr.R
import com.keepcoding.guedr.fragment.CityListFragment
import com.keepcoding.guedr.fragment.CityPagerFragment
import com.keepcoding.guedr.model.City

class ForecastActivity : AppCompatActivity(), CityListFragment.OnCitySelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        //chuleta metricas dispositivo
        val metrica = resources.displayMetrics
        val width = metrica.widthPixels
        val height = metrica.heightPixels
        val dpWidth = (width / metrica.density).toInt()
        val dpHeight = (height / metrica.density).toInt()
        val model = Build.MODEL
        val AndroidV = Build.VERSION.SDK_INT



        //comprobamos que en la interfz tenemos un framne layout city_list_fragment
        if (findViewById<View>(R.id.city_list_fragment) != null) {

            // Comprobamos primero que no tenemos ya añadido el fragment a nuestra jerarquía
            if (fragmentManager.findFragmentById(R.id.city_list_fragment) == null) {
                val fragment = CityListFragment.newInstance()
                fragmentManager.beginTransaction()
                        .add(R.id.city_list_fragment, fragment)
                        .commit()
            }
        }

        //hcemos lo smismo pero con el fragment de cityPager
        if (findViewById<View>(R.id.fragment_city_pager) != null) {
            if (fragmentManager.findFragmentById(R.id.fragment_city_pager) == null) {
                val fragment = CityPagerFragment.newInstance(0)
                fragmentManager.beginTransaction()
                        .add(R.id.fragment_city_pager, fragment)
                        .commit()
            }

        }
    }

    override fun onCitySelected(city: City?, position: Int) {
        val cityPagerFragment = fragmentManager.findFragmentById(R.id.fragment_city_pager) as CityPagerFragment

        if (cityPagerFragment == null) {
              startActivity(CityPagerActivity.intent(this, position))
        } else{
            //decimos al viewPager que se mueva
            cityPagerFragment.moveToCity(position)
        }


    }


}
