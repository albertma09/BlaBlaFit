package com.copernic.blablafit.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.copernic.blablafit.R
import com.copernic.blablafit.Utils.UtilsFunctions
import com.copernic.blablafit.databinding.ActivityMainInicioBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivityInicio : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var bin: ActivityMainInicioBinding

    companion object {
        private const val TAG = "MainActivityInicio"
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        //Splash screen
        Thread.sleep(2000)
        setTheme(R.style.Theme_Blablafit)

        super.onCreate(savedInstanceState)
        bin = ActivityMainInicioBinding.inflate(layoutInflater)
        setContentView(bin.root)
        supportActionBar!!.hide()
        bin.logoApp.animate().rotation(360f).setDuration(5000).start()

        auth = Firebase.auth
        bin.iniciar.setOnClickListener {
            login()
        }

        //Abrir ventana recuperar contraseña
        bin.recuperarPassword.setOnClickListener() {
            intent = Intent(this, Enviar_Contrasenya::class.java)
            startActivity(intent)

        }

        //Abrir ventana registro
        bin.registro.setOnClickListener() {
            intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload();
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        reload()
    }


    private fun reload() {
        val user = auth.currentUser

        user?.let {
            val nom = user.displayName ?: run { "sense nom" }
            //bin.tvMAMissatges.setText("Usuari email: ${user.email}\n$nom")
        } ?: run {
            //bin.tvMAMissatges.setText("Usuari: no assignat")

        }
    }

    //Iniciar Sesion
    private fun login() {

        val mail = bin.usuario.text.toString()
        val pass = bin.password.text.toString()
        val snackbar = bin.snackbar


        //Verificar contraseña y password
        if (mail.isNotEmpty() && pass.isNotEmpty()) {
            if (UtilsFunctions.checkMail(mail, pass, snackbar)) {
                auth.signInWithEmailAndPassword(mail, pass)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            intent = Intent(this, MainApp::class.java)
                            startActivity(intent)
                            UtilsFunctions.showAlertConnect(this)


                        } else {
                            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                            UtilsFunctions.showAlert(this)
                        }
                    }

            }

        } else {
            Snackbar.make(bin.snackbar, "Faltan datos por completar", Snackbar.LENGTH_SHORT).show()
        }

    }


}