package com.copernic.blablafit.Utils

import android.location.Location
import android.location.LocationListener
import android.os.Bundle

 class userLocation : LocationListener {
    override fun onLocationChanged(location: Location) {
        print("test")
        // Se ha recibido una nueva ubicación. Procesar la ubicación aquí.
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        print("Test")
    }
    override fun onProviderEnabled(provider: String) {
        print("Test")
    }
    override fun onProviderDisabled(provider: String) {
        print("Test")
    }
}