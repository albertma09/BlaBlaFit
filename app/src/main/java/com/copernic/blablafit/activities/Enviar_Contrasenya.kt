package com.copernic.blablafit.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.copernic.blablafit.R
import com.copernic.blablafit.databinding.ActivityEnviarContrasenyaBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Enviar_Contrasenya : AppCompatActivity() {

    private lateinit var bin: ActivityEnviarContrasenyaBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enviar_contrasenya)
        bin = ActivityEnviarContrasenyaBinding.inflate(layoutInflater)
        setContentView(bin.root)
        supportActionBar!!.hide()

        /**Confirmar datos*/
        bin.recuperar.setOnClickListener {
            recuperar()
        }

        /**Enviar a la pantalla recuperar contraseña*/
        bin.textView2.setOnClickListener{
            intent = Intent(this, Registro::class.java)
            startActivity(intent)

        }


    }

    /**Recuperar contraseña*/
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
