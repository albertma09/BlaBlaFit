package com.copernic.blablafit.activities

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.copernic.blablafit.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Maps : FragmentActivity(), OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener {


    private lateinit var map: GoogleMap
    private var nutricionistas: Boolean = false
    private var gym: Boolean = false
    private val db = Firebase.firestore
    private val ubicaciones = db.collection("Ubicaciones")


    companion object {
        const val REQUEST_CODE_LOCATION = 0
    }

    /**Cargar el mapa*/
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        createMarker()
        map.setOnMyLocationButtonClickListener(this)
        enableMyLocation()
    }

    /**Poner un marcador en ciudad*/
    private fun createMarker() {
        val location: Location
        val favoritePlace = LatLng(41.56667, 2.01667)
        map.addMarker(MarkerOptions().position(favoritePlace).title("Terrassa"))
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(favoritePlace, 18f),
            4000,
            null
        )
    }

    /**Crear activity*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_maps)
        createMapFragment()
        val btnNutri: Button = findViewById(R.id.Nutricion)
        val btnGym: Button = findViewById(R.id.Gimnasios)
        btnNutri.setOnClickListener { filtrarNutricionista() }
        btnGym.setOnClickListener { filtrarGimnasios() }
        val a = ubicaciones.document("Gimnasios")

    }

    /**Insertar map fragment*/
    private fun createMapFragment() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**Comprobar permisos*/
    private fun isPermissionsGranted() = ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    /**Activar geolocalizacion*/
    private fun enableMyLocation() {
        if (!::map.isInitialized) return
        if (isPermissionsGranted()) {
            map.isMyLocationEnabled = true
        } else {
            requestLocationPermission()
        }
    }

    /**Solicitar permisos de geolocalizacion*/
    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            Toast.makeText(this, "Ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION
            )
        }
    }

    /**Verificar permisos seleccionados por el usuario*/
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_LOCATION -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                map.isMyLocationEnabled = true
            } else {
                Toast.makeText(
                    this,
                    "Para activar la localización ve a ajustes y acepta los permisos",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {}
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if (!::map.isInitialized) return
        if (!isPermissionsGranted()) {
            map.isMyLocationEnabled = false
            Toast.makeText(
                this,
                "Para activar la localización ve a ajustes y acepta los permisos",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    /**Mover camara a la ubicacion actual*/
    override fun onMyLocationButtonClick(): Boolean {
        return false
    }

    /**Mostrar nutricionistas en el mapa*/
    /**Deben estar registrados en la base de datos*/
    private fun filtrarNutricionista() {

        ubicaciones.document("Nutricionistas").get().addOnSuccessListener {
            val datos = it.data as Map<String, *>
            val al = datos["Nutricionistas"] as ArrayList<*>
            for (l in 0 until al.size) {
                val b = al[l] as Map<String, String>
                val latitud = b["Latitud"]!!.toDouble()
                val longitud = b["Longitud"]!!.toDouble()
                val nombre = b["Nombre"]
                val latlan = LatLng(latitud, longitud)
                map.addMarker(
                    MarkerOptions().position(latlan).title(nombre)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                )

            }


        }


    }
    /**Mostrar gimnasios*/
    /**Deben estar registrados en la base de datos*/
    private fun filtrarGimnasios() {
        ubicaciones.document("Gimnasios").get().addOnSuccessListener {
            val datos = it.data as Map<String, *>
            val al = datos["Gimnasios"] as ArrayList<*>
            for (l in 0 until al.size) {
                val b = al[l] as Map<String, String>
                val latitud = b["Latitud"]!!.toDouble()
                val longitud = b["Longitud"]!!.toDouble()
                val nombre = b["Nombre"]
                val latlan = LatLng(latitud, longitud)
                map.addMarker(
                    MarkerOptions().position(latlan).title(nombre)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                )

            }


        }


    }


}


