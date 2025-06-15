package com.ajrr.myrickmorty

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ajrr.myrickmorty.auth.LoginActivity
import com.ajrr.myrickmorty.auth.LoginAlternateActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("SPLASH", "Entrando a SplashActivity")

        // 1. Configurar Remote Config
        val remoteConfig = Firebase.remoteConfig
        remoteConfig.setConfigSettingsAsync(
            remoteConfigSettings {
                minimumFetchIntervalInSeconds = 0 // Forzar actualización siempre en pruebas
            }
        )

        // 2. Obtener y activar los valores de Remote Config
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val useAlternate = remoteConfig.getBoolean("use_alternate_login")
                Log.d("SPLASH", "use_alternate_login: $useAlternate")

                val intent = if (useAlternate) {
                    Intent(this, LoginAlternateActivity::class.java)
                } else {
                    Intent(this, LoginActivity::class.java)
                }

                startActivity(intent)
                finish()
            } else {
                Log.e("SPLASH", "Remote Config fetch failed", task.exception)
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }
}
