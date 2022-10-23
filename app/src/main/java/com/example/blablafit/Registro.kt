package com.example.blablafit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
class Registro : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var btn_registrar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        btn_registrar= findViewById(R.id.registrar)
        btn_registrar.setOnClickListener { registro() }
    }

    private fun registro() {

        email = findViewById(R.id.mail)
        password = findViewById(R.id.password)
        val mail = email.text.toString()
        val pass = password.text.toString()



        Log.i("TAG", mail)
        Log.i("TAG", pass)


        if (mail.isNotEmpty() && pass.isNotEmpty()) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail, pass)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Nuevo usuario registrado", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this,MainActivityInicio::class.java)
                        startActivity(intent)
                        //showAlertConnect()


                    } else {
                        Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show()
                        //showAlert()
                    }
                }
        }


    }
}