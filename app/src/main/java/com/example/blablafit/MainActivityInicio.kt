package com.example.blablafit

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.example.blablafit.UtilsFunctions
import com.example.blablafit.databinding.ActivityMainInicioBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivityInicio : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var lbl_registro: TextView
    private lateinit var btn_login: Button
    private lateinit var btn_recupera_psw: TextView
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

        bin.logoApp.animate().rotation(360f).setDuration(5000).start()
        // Initialize Firebase Auth
        auth = Firebase.auth

        bin.iniciar.setOnClickListener {
            login()
        }



        bin.recuperarPassword.setOnClickListener() {
            //intent = Intent(this, Enviar_Contrasenya::class.java)
            //startActivity(intent)
            intent = Intent(this, RecyclerViewRutinas::class.java)
            startActivity(intent)
        }

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


    private fun login() {

        val mail = bin.usuario.text.toString()
        val pass = bin.password.text.toString()
        val snackbar = bin.snackbar



        if (mail.isNotEmpty() && pass.isNotEmpty()) {
            if (UtilsFunctions.checkMail(mail, pass, snackbar)) {
                auth.signInWithEmailAndPassword(mail, pass)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
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