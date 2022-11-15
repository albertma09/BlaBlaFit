package com.example.blablafit

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.blablafit.databinding.ActivityMainAppBinding

class MainApp : AppCompatActivity() {
    lateinit var binding: ActivityMainAppBinding
    lateinit var toggle: ActionBarDrawerToggle

    val fragmentManager = supportFragmentManager

    lateinit var fragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //val navHostFragment =
        //  supportFragmentManager.findFragmentById(R.id.nav_graph)
        //val navController = navHostFragment.navController
        binding.apply {
            toggle =
                ActionBarDrawerToggle(this@MainApp, drawerLayout, R.string.open, R.string.close)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
            fragment = Principal()


            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            var transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, Principal())
            transaction.addToBackStack(null)
            transaction.commit()
            var profile = findViewById<ImageView>(R.id.imageView8)



            navView.setNavigationItemSelectedListener {
                when (it.itemId) {

                    R.id.controlSemanal -> {
                        fragment = PerfilPersonal()


                    }
                    R.id.objetivoCalorias -> {
                        fragment = DadesPersonals()
                        Toast.makeText(this@MainApp, "Second Item Clicked", Toast.LENGTH_SHORT)
                            .show()
                    }
                    R.id.Objetivos -> {
                        fragment = Objetivo()
                        Toast.makeText(this@MainApp, "third Item Clicked", Toast.LENGTH_SHORT)
                            .show()
                    }
                    R.id.inicioMenu -> {
                        fragment = Principal()
                        Toast.makeText(this@MainApp, "third Item Clicked", Toast.LENGTH_SHORT)
                            .show()
                    }
                    R.id.CerrarMenu -> {
                        //Meter pop up de cierre de sesion
                        Toast.makeText(this@MainApp, "third Item Clicked", Toast.LENGTH_SHORT)
                            .show()
                    }
                    R.id.imageView8 -> {
                        //imagen perfil
                        fragment = PerfilPersonal()
                        Toast.makeText(this@MainApp, "third Item Clicked", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                transaction = fragmentManager.beginTransaction()
                //var action = PrincipalDirections.actionPrincipalToDietas()
                transaction.replace(R.id.fragmentContainerView, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
                drawerLayout.close()
                true


            }

        }

    }

    fun clicked(view: View) {
        Log.i("Test: ", "Ha entrado")
        when (view.id) {
            R.id.imageView8 -> fragment = PerfilPersonal2()
            R.id.inicioMenu -> fragment = Principal()
            R.id.dietas -> fragment = Dietas()
            R.id.rutinas_menu -> fragment = Rutinas2()
            R.id.alimentacion -> fragment = InsercionAlimentos()
            R.id.mapa -> {
                abrirMapa(41.56441650669841, 2.010311059912172, "nutricionista")
            }
            R.id.dias_3, R.id.dias_4, R.id.dias_5, R.id.dias_6 -> fragment = Rutinas3()
            R.id.casa, R.id.gym -> fragment = rutinas4()
        }

        val transaction = fragmentManager.beginTransaction()

        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        binding.drawerLayout.close()


    }

    private fun abrirMapa(latitud: Double, longitud: Double, filtro: String = "nutricionista") {
        val gmmIntentUri = Uri.parse("geo:${latitud}, ${longitud}?q=${filtro}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            true
        }
        return super.onOptionsItemSelected(item)
    }
}