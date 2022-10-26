package com.example.blablafit

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivityInicio : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var lbl_registro: TextView
    private lateinit var btn_login: Button
    private lateinit var btn_recupera_psw: TextView

    override fun onCreate(savedInstanceState: Bundle?) {


        //Splash screen
        Thread.sleep(2000)
        setTheme(R.style.Theme_Blablafit)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_inicio)
        //Analytics Event
        btn_recupera_psw = findViewById(R.id.recuperar_password)
        btn_login = findViewById(R.id.iniciar)
        lbl_registro = findViewById(R.id.registro)
        email = findViewById(R.id.usuario)
        password = findViewById(R.id.password)
        lbl_registro.setOnClickListener {
            registro()
        }

        btn_login.setOnClickListener {
            login()
        }

        btn_recupera_psw.setOnClickListener {
            recupera_psw()
        }


    }


    private fun registro() {
        intent = Intent(this, Registro::class.java)
        startActivity(intent)
    }


    private fun recupera_psw() {
        intent = Intent(this, Recupera_Contrasenya::class.java)
        startActivity(intent)
    }

    private fun login() {
        val mail = email.text.toString()
        val pass = password.text.toString()
        if (mail.isNotEmpty() && pass.isNotEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(mail, pass)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
                        showAlertConnect()

                    } else {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                        showAlert()
                    }
                }
        }

    }

    private fun showAlertConnect() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Correct")
        builder.setMessage("Se ha registrado")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


}