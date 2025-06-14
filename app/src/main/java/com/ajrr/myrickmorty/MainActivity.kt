package com.ajrr.myrickmorty

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.ajrr.myrickmorty.data.remote.RetrofitInstance
import com.ajrr.myrickmorty.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

  
        lifecycleScope.launch {
            try {
                val episodes = RetrofitInstance.api.getEpisodes().results
                episodes.forEach {
                    Log.d("API", "Capítulo: ${it.name}")
                }
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error al obtener episodios: ${e.localizedMessage}")
            }
        }
    }
}