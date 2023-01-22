package com.copernic.blablafit.activities

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
import com.copernic.blablafit.R
import com.copernic.blablafit.Utils.UtilsFunctions
import com.copernic.blablafit.databinding.ActivityRegistroBinding
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
    /**

    Método que se encarga de registrar un nuevo usuario en la aplicación.

    Recoge los datos del nombre de usuario, correo electrónico y contraseña introducidos por el usuario.

    Verifica que los datos introducidos son correctos y no están vacíos, y si es así, se registra el usuario en Firebase y en la base de datos de la aplicación.

    Si el usuario ya existe o faltan datos por introducir, se muestra un mensaje de error al usuario.
     */
    private fun registro() {
        val user = bin.username.text.toString()
        val mail = bin.mail.text.toString()
        val pass = bin.password.text.toString()
        auth = Firebase.auth

        if (mail.isNotEmpty() && pass.isNotEmpty() && user.isNotEmpty()) {
            if (com.copernic.blablafit.Utils.UtilsFunctions.checkMail(mail, pass, bin.password)) {

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



    /**

    Método encargado de registrar un nuevo usuario en la base de datos de Firebase

    Crea un HashMap con los datos necesarios para registrar al usuario y los agrega a la colección "usuarios" en Firebase
     */
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
    /**

    Método encargado de iniciar sesión en la aplicación
    Crea un Intent para navegar al MainActivityInicio y lo inicia
     */
    private fun login() {
        val intent = Intent(this, MainActivityInicio::class.java)
        startActivity(intent)

    }
}