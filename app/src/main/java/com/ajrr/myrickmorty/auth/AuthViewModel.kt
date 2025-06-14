package com.ajrr.myrickmorty.auth

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun login(email: String, password: String, callback: (Boolean, String) -> Unit) {
        if (email.isEmpty() || password.isEmpty()) {
            callback(false, "Completa todos los campos")
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    callback(true, "Inicio de sesión exitoso")
                } else {
                    callback(false, "Error: ${it.exception?.localizedMessage}")
                }
            }
    }

    fun register(email: String, password: String, callback: (Boolean, String) -> Unit) {
        if (email.isEmpty() || password.isEmpty()) {
            callback(false, "Completa todos los campos")
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    callback(true, "Registro exitoso")
                } else {
                    callback(false, "Error: ${it.exception?.localizedMessage}")
                }
            }
    }
}