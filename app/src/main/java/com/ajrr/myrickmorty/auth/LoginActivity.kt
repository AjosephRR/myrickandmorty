package com.ajrr.myrickmorty.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ajrr.myrickmorty.MainActivity
import com.ajrr.myrickmorty.R
import com.ajrr.myrickmorty.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    //IN GITHUB - FIREBASE //

    private lateinit var binding: ActivityLoginBinding
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.loginButton.setOnClickListener{

         val email = binding.emailEditText.text.toString().trim()
         val password = binding.passwordEditText.text.toString().trim()
            authViewModel.login(email,password){
                success, message ->
                if (success){
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()

                }
            }
        }

        binding.registerLink.setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
        }

    }
}