package com.ajrr.myrickmorty

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig

class SplashActivity : AppCompatActivity () {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Firebase.remoteConfig.setDefaultsAsync(
            mapOf("use_alternate_login" to false)
        )

        Firebase.remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val useAlt = Firebase.remoteConfig.getBoolean("use_alternate_login")

                    val loginIntent = if (useAlt) {
                        Intent(this, LoginAlternateActivity::class.java)
                    } else {
                        Intent(this, LoginActivity::class.java)
                    }

                    startActivity(loginIntent)
                    finish()
                }
            }

    }


}