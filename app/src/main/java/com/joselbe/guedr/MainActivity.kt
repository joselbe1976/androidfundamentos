package com.joselbe.guedr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    //TAG name for Logs
    val TAG = MainActivity::class.java.canonicalName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v(TAG,"onCreate executing")

        if (savedInstanceState != null){
            //extraemos el valor
            Log.v(TAG, "savedInstanceState no es null")
            Log.v(TAG, "El valor recuperado es ${savedInstanceState.getString("Key1")}")  //extraigo el valor de una clave.

        }
        else{
            Log.v(TAG, "savedInstanceState es null")
        }
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.v(TAG,"Ejecuta onSaveInstanceState")

        /*
        if (outState != null){
            // Aqui podemos star seguros que podemos llamar metodos
            outState.putString("Key1", "Valor Joselbe")  //save values
        }
        */
        outState?.putString("Key1", "Valor Joselbe") //esto es lo mismo que lo de arriba dado que es un opcional

    }

}
