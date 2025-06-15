package com.ajrr.myrickmorty.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ajrr.myrickmorty.MainActivity
import com.ajrr.myrickmorty.databinding.ActivityLoginAlternateBinding
import com.google.firebase.auth.FirebaseAuth

class LoginAlternateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginAlternateBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginAlternateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnLoginAlt.setOnClickListener {
            val email = binding.editEmailAlt.text.toString()
            val password = binding.editPasswordAlt.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Completa los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}