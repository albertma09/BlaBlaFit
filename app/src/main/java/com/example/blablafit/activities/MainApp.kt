package com.example.blablafit.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.blablafit.*
import com.example.blablafit.databinding.ActivityMainAppBinding
import com.example.blablafit.fragmentsApp.*


class MainApp : AppCompatActivity() {
    lateinit var binding: ActivityMainAppBinding
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var pBar : ProgressBar
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController : NavController
    private val fragmentManager = supportFragmentManager
    var progres : Int = 0
    lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        toggle = ActionBarDrawerToggle(this@MainApp, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.controlSemanal -> {
                    //fragment = PerfilPersonal()
                    navController.navigate(R.id.action_global_perfilPersonal2)


                }
                R.id.objetivoCalorias -> {

                    // navHostFragment.navigate(

                    //fragment = DadesPersonals()
                    navController.navigate(R.id.action_global_dadesPersonals)
                    //Toast.makeText(this@MainApp, "Second Item Clicked", Toast.LENGTH_SHORT)
                    //    .show()
                }
                R.id.objetivos -> {
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
            //transaction = fragmentManager.beginTransaction()
            //transaction.replace(R.id.fragmentContainerView, fragment)
            //transaction.addToBackStack(null)
            //transaction.commit()
            binding.drawerLayout.close()
            true


        }


            //fragment = Principal()


            //var transaction = fragmentManager.beginTransaction()
            //transaction.replace(R.id.fragmentContainerView, Principal())
            //transaction.addToBackStack(null)
            //transaction.commit()
            //var profile = findViewById<ImageView>(R.id.imageView8)








    }

    fun clicked(view: View) {
        Log.i("Test: ", "Ha entrado")
        when (view.id) {
            R.id.datos_fisicos -> true
            R.id.dadesPersonals -> navController.navigate(R.id.action_global_dadesPersonals)
            R.id.imageView8 -> fragment = PerfilPersonal2()
            R.id.inicioMenu -> fragment = Principal()
            R.id.mapa -> {
                abrirMapa(41.56441650669841, 2.010311059912172, "nutricionista")
            }

            R.id.agua->{
                val i : ProgressBar = findViewById(R.id.indicador)
                progres = i.progress+10
                i.progress= progres
                Toast.makeText(this,progres.toString(),Toast.LENGTH_LONG).show()
                if(i.progress == i.max){
                    i.progress =0
                    Toast.makeText(this,"Has bebido 2 litros",Toast.LENGTH_LONG).show()
                }

            }
        }

        var transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        binding.drawerLayout.close()


    }

//    override fun onBackPressed() {
//
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("Cerrar sesion")
//        builder.setMessage("Quieres salir?")
//        builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, id ->
//            run {
//                val intent = Intent(this, MainActivityInicio::class.java)
//                startActivity(intent)
//            }
//        })
//
//        builder.setNegativeButton("Cancelar", null)
//        val dial: AlertDialog = builder.create()
//        dial.show()
//    }


    private fun abrirMapa(latitud: Double, longitud: Double, filtro: String = "nutricionista") {
        val gmmIntentUri = Uri.parse("geo:${latitud}, ${longitud}?q=${filtro}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

    private fun enviarMensaje() {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Prueba de intent implicita")
            type = "text/plain"
        }


        if (sendIntent.resolveActivity(packageManager) != null) {
            startActivity(sendIntent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            true
        }
        return super.onOptionsItemSelected(item)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }
}