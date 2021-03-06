package com.cbellmont.ejemploandroid8

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cbellmont.ejemploandroid8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "Hola"
    }

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cargarPreferencias()?.let {
            binding.editText.setText(it)
        }
    }

    override fun onStop() {
        guardarPreferencias(binding.editText.toString())
        super.onStop()
    }

    private fun cargarPreferencias() : String? {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getString("hola", "")
    }

    private fun guardarPreferencias(string : String) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        /* Lo de abajo es una manera más elegante de poner esto mismo.
        var sharedPrefEditable = sharedPref.edit()
        sharedPrefEditable.putString("NOMBRE", "valor guardado")
        sharedPrefEditable.commit() */

        with (sharedPref.edit()) {
            putString(TAG, string)
            commit()
        }
    }
}