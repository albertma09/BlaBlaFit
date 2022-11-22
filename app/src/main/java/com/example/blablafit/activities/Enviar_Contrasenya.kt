package com.example.blablafit

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.blablafit.databinding.ActivityEnviarContrasenyaBinding
import com.example.blablafit.databinding.ActivityMainInicioBinding
import com.google.android.material.tabs.TabLayout.TabGravity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Enviar_Contrasenya : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var btn_enviar_contrasena: Button
    private lateinit var correo : String
    private lateinit var auth: FirebaseAuth
    private lateinit var bin: ActivityEnviarContrasenyaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enviar_contrasenya)
        bin = ActivityEnviarContrasenyaBinding.inflate(layoutInflater)
        setContentView(bin.root)
        supportActionBar!!.hide()


        bin.recuperar.setOnClickListener {
            recuperar()
        }
        //bin.editTextTextPersonName.setOnClickListener { recuperar() }

    }

    private fun recuperar(){
        val mail = bin.editTextTextPersonName.text.toString()

        if(mail.isNotEmpty()){

            Firebase.auth.sendPasswordResetEmail(mail).addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    intent = Intent(this, MainActivityInicio::class.java)
                    startActivity(intent)
                }
            }

        }
    }


    }
