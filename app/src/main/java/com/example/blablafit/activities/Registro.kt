package com.example.blablafit.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.blablafit.R
import com.example.blablafit.Utils.UtilsFunctions
import com.example.blablafit.databinding.ActivityRegistroBinding
import com.google.firebase.auth.FirebaseAuth

class Registro : AppCompatActivity() {

    private lateinit var bin: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        supportActionBar!!.hide()
        bin.registrar.setOnClickListener { registro() }
        bin.iniciarSesion.setOnClickListener {
            login()
        }

    }

    private fun registro() {

        val mail = bin.mail.text.toString()
        val pass = bin.password.text.toString()


        if (mail.isNotEmpty() && pass.isNotEmpty()) {
            if (UtilsFunctions.checkMail(mail, pass, bin.password)) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail, pass)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            bin.registrar.setEnabled(false)
                            Toast.makeText(this, "Nuevo usuario registrado", Toast.LENGTH_SHORT)
                                .show()
                            val intent = Intent(this, MainActivityInicio::class.java)
                            startActivity(intent)
                            //showAlertConnect()


                        } else {
                            Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show()
                            //showAlert()
                        }
                    }
            }
        } else {
            Toast.makeText(this, "Faltan datos por introducir", Toast.LENGTH_SHORT).show()

        }


    }

    private fun login() {
        val intent = Intent(this, MainActivityInicio::class.java)
        startActivity(intent)

    }
}