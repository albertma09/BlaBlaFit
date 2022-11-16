package com.example.blablafit.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blablafit.R
import com.example.blablafit.Utils.UtilsFunctions
import com.example.blablafit.databinding.ActivityRecuperaContrasenyaBinding
import com.google.firebase.auth.FirebaseAuth

class Enviar_Contrasenya : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enviar_contrasenya)
        supportActionBar!!.hide()
    }
}