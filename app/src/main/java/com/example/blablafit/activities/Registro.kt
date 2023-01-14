package com.example.blablafit.activities

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.blablafit.R
import com.example.blablafit.Utils.UtilsFunctions
import com.example.blablafit.databinding.ActivityRegistroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class Registro : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var bin: ActivityRegistroBinding
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bin = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(bin.root)
        db = Firebase.firestore
        supportActionBar!!.hide()
        bin.registrar.setOnClickListener { registro() }
        bin.iniciarSesion.setOnClickListener {
            login()
        }

    }

    private fun registro() {
        val user = bin.username.text.toString()
        val mail = bin.mail.text.toString()
        val pass = bin.password.text.toString()
        auth = Firebase.auth

        if (mail.isNotEmpty() && pass.isNotEmpty() && user.isNotEmpty()) {
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
                            registrarUserBase()


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




    private fun registrarUserBase(){
        val user = bin.username.text.toString()
        val mail = bin.mail.text.toString()
        val pass = bin.password.text.toString()

        auth = Firebase.auth
        val usuario = hashMapOf(
            "nombre_usuario" to user,
            "email" to mail,
            "passwd" to pass,
            "genero" to null,
            "altura" to null,
            "lista_peso" to null,
            "edad" to null,
            "lista_objetivos" to null,
            "rutina" to null,
            "entrenoSemanal" to null,
            "lugar" to null,
        )
        db.collection("usuarios").document(auth.uid.toString()).set(usuario)



    }

    private fun login() {
        val intent = Intent(this, MainActivityInicio::class.java)
        startActivity(intent)

    }
}