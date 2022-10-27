package com.example.blablafit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.blablafit.databinding.ActivityRegistroBinding
import com.google.firebase.auth.FirebaseAuth

class Registro : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var btn_registrar: Button
    private lateinit var lbl_iniciar_sesio: TextView
    private lateinit var username: EditText
    private lateinit var bin: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        btn_registrar = findViewById(R.id.registrar)
        lbl_iniciar_sesio = findViewById(R.id.iniciar_sesion)
        btn_registrar.setOnClickListener { registro() }
        lbl_iniciar_sesio.setOnClickListener {
            login()
        }
    }

    private fun registro() {

        email = findViewById(R.id.mail)
        password = findViewById(R.id.password)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)


        val mail = email.text.toString()
        val pass = password.text.toString()
        val nombreUsuario = username.text.toString()




        Log.i("TAG", mail)
        Log.i("TAG", pass)


        if (mail.isNotEmpty() && pass.isNotEmpty()) {
            if (UtilsFunctions.checkMail(mail, pass, password)) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail, pass)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
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
            } else {
                Toast.makeText(this, "Faltan datos por introducir", Toast.LENGTH_SHORT).show()

            }
        }


    }

    private fun login() {
        val intent = Intent(this, MainActivityInicio::class.java)
        startActivity(intent)

    }
}