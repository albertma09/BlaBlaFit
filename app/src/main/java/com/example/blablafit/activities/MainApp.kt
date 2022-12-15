package com.example.blablafit.activities

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.blablafit.*
import com.example.blablafit.databinding.ActivityMainAppBinding
import com.example.blablafit.fragmentsApp.*


class MainApp : AppCompatActivity() {
    lateinit var binding: ActivityMainAppBinding
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var pBar: ProgressBar
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController
    private val fragmentManager = supportFragmentManager
    var progres: Int = 0
    lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        toggle =
            ActionBarDrawerToggle(this@MainApp, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.controlSemanal -> {
                    //fragment = PerfilPersonal()
                    Navigation.findNavController(binding.navHostFragment)
                        .navigate(R.id.action_global_perfilPersonal2)
                    //navController.navigate(R.id.action_global_perfilPersonal2)


                }
                R.id.objetivoCalorias -> {

                    // navHostFragment.navigate(

                    //fragment = DadesPersonals()
                    Navigation.findNavController(binding.navHostFragment)
                        .navigate(R.id.action_global_dadesPersonals)
                    //Toast.makeText(this@MainApp, "Second Item Clicked", Toast.LENGTH_SHORT)
                    //    .show()
                }
                R.id.objetivos -> {

                    Navigation.findNavController(binding.navHostFragment)
                        .navigate(R.id.action_global_objetivo)
                    Toast.makeText(this@MainApp, "third Item Clicked", Toast.LENGTH_SHORT)
                        .show()
                }
                R.id.inicioMenu -> {

                    Navigation.findNavController(binding.navHostFragment)
                        .navigate(R.id.action_global_principal)

                }
                R.id.CerrarMenu -> {
                    //Meter pop up de cierre de sesion
                    Toast.makeText(this@MainApp, "third Item Clicked", Toast.LENGTH_SHORT)
                        .show()
                }
                R.id.imageView8 -> {
                    //imagen perfil

                    Navigation.findNavController(binding.navHostFragment)
                        .navigate(R.id.action_global_perfilPersonal2)
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
            R.id.datos_fisicos -> Navigation.findNavController(binding.navHostFragment)
                .navigate(R.id.action_global_datos_fiicos)
            R.id.dadesPersonals -> navController.navigate(R.id.action_global_dadesPersonals)
            R.id.imageView8 -> navController.navigate(R.id.action_global_perfilPersonal2)
            R.id.inicioMenu -> Navigation.findNavController(binding.navHostFragment)
                .navigate(R.id.action_global_principal)
            R.id.mapa -> {
                //val gps : GPSTracker
                abrirMapa(41.56441650669841, 2.010311059912172, "nutricionista")
            }
            R.id.CerrarMenu -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Cerrar sesion")
                builder.setMessage("Quieres salir?")
                builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, id ->
                    run {
                        val intent = Intent(this, MainActivityInicio::class.java)
                        startActivity(intent)
                    }
                })

                builder.setNegativeButton("Cancelar", null)
                val dial: AlertDialog = builder.create()
                dial.show()

            }

        }

        binding.drawerLayout.close()

        //var transaction = fragmentManager.beginTransaction()
        //transaction.replace(R.id.nav_host_fragment, fragment)
        //transaction.addToBackStack(null)
        //transaction.commit()
        //binding.drawerLayout.close()


    }

    fun getLocation(){
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if((ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),2)
        }
        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5f,this)
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