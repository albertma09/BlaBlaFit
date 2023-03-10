package com.copernic.blablafit.activities

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import coil.api.load
import com.copernic.blablafit.R
import com.copernic.blablafit.databinding.ActivityMainAppBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage


class MainApp : AppCompatActivity()  {
    lateinit var binding: ActivityMainAppBinding

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var pBar: ProgressBar
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val fragmentManager = supportFragmentManager
    var latitude = 0;
    var longitude = 0;
    var progres: Int = 0
    lateinit var fragment: Fragment
    private lateinit var tvLatitude: String
    private lateinit var tvLongitude: String
    private lateinit var locationCallback: LocationCallback
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        val storage = FirebaseStorage.getInstance()
        val reference = storage.getReference("usersImages/${auth.uid.toString()}/perfil")


        reference.downloadUrl.addOnSuccessListener {
            binding.navView.findViewById<ImageView>(R.id.imageView8).load(it)

        }
        db.collection("usuarios").document(auth.uid.toString()).get().addOnSuccessListener {
            println(it.get("lista_peso").toString())
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {

                for (location in p0.locations){
                    getCurrentLocation()
                    imprimirUbicacion()
                }


            }

        }



        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        toggle =
            ActionBarDrawerToggle(this@MainApp, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        binding.navView.setNavigationItemSelectedListener {

            when (it.itemId) {

                R.id.controlSemanal -> {
                    Navigation.findNavController(binding.navHostFragment)
                        .navigate(R.id.action_global_perfilPersonal2)



                }
                R.id.objetivoCalorias -> {


                    Navigation.findNavController(binding.navHostFragment)
                        .navigate(R.id.action_global_dadesPersonals)

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
                    /**Meter pop up de cierre de sesion*/
                    Toast.makeText(this@MainApp, "third Item Clicked", Toast.LENGTH_SHORT)
                        .show()
                }
                R.id.imageView8 -> {
                    /**imagen perfil*/

                    Navigation.findNavController(binding.navHostFragment)
                        .navigate(R.id.action_global_perfilPersonal2)
                    Toast.makeText(this@MainApp, "third Item Clicked", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            binding.drawerLayout.close()
            true


        }





    }
    fun imprimirUbicacion(){
        Toast.makeText(this,tvLongitude,Toast.LENGTH_SHORT).show()
    }

    fun clicked(view: View) {
        when (view.id) {
            R.id.datos_fisicos -> Navigation.findNavController(binding.navHostFragment)
                .navigate(R.id.action_global_datos_fiicos)
            R.id.dadesPersonals -> navController.navigate(R.id.action_global_dadesPersonals)
            R.id.imageView8 -> navController.navigate(R.id.action_global_perfilPersonal2)
            R.id.inicioMenu -> Navigation.findNavController(binding.navHostFragment)
                .navigate(R.id.action_global_principal)
            R.id.mapa -> {
                val intent = Intent(this, Maps::class.java)
                startActivity(intent)

            }
            R.id.CerrarMenu -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Cerrar sesion")
                builder.setMessage("Quieres salir?")
                builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, id ->
                    run {
                        FirebaseAuth.getInstance().signOut()
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


    }

    /**Obtener posicion actual*/
    private fun getCurrentLocation() {
        if (checkPermission()) {
            if (ActivityCompat.checkSelfPermission(
                    this, android.Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermission()
            }

            if (isLocationEnabled()) {


                fusedLocationProviderClient.lastLocation.addOnCompleteListener(this) { task ->
                    val location: Location? = task.result
                    if (location == null) {
                        Toast.makeText(applicationContext, "Null Received", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        tvLatitude = location.latitude.toString()
                        tvLongitude = location.longitude.toString()


                    }
                }
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {

        }
        requestPermission()

    }

    companion object {
        private const val PERMISSION_REQUEST_ACCESS_LOCATION = 1000
    }

    /**Solicitar permisos*/
    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_REQUEST_ACCESS_LOCATION
        )
    }

    /**Comprobar permisos*/
    private fun checkPermission(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            return true
        }
        return false
    }

    /**Verificar la confirmacion del usuario*/
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_REQUEST_ACCESS_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext, "Granted", Toast.LENGTH_SHORT)


            } else {
                Toast.makeText(applicationContext, "Denied", Toast.LENGTH_SHORT)

            }
        }
    }


    /**Verificar que la geolocalizacion est?? activada*/
    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            true
        }
        return super.onOptionsItemSelected(item)
    }

}