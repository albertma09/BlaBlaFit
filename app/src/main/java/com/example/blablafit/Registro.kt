package com.example.blablafit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Blablafit)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_inicio)
    }
}