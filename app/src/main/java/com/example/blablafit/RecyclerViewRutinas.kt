package com.example.blablafit

import android.R
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blablafit.databinding.ActivityRecyclerViewBinding


data class Contacto(val nombre:String)

class RecyclerViewRutinas : AppCompatActivity() {

    private lateinit var binding:ActivityRecyclerViewBinding
    private  var listapersonajes:MutableList<Contacto> = mutableListOf()
    private lateinit var recycler:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listapersonajes.add(Contacto(("Naruto Uzumaki")))
        listapersonajes.add(Contacto(("Sasuke Uchiha")))
        listapersonajes.add(Contacto(("Personaje")))
        listapersonajes.add(Contacto(("Sakura Haruno")))
        listapersonajes.add(Contacto(("Sai")))
        listapersonajes.add(Contacto(("Hinata Hyūga")))
        listapersonajes.add(Contacto(("Kiba Inuzuka")))
        listapersonajes.add(Contacto(("Chōji Akimichi")))
        listapersonajes.add(Contacto(("Rock Lee")))



    }

    override fun onStart() {
        super.onStart()
        val manager = LinearLayoutManager(this)
        recycler.setLayoutManager(manager)
        recycler.setHasFixedSize(true)
        val adapter = ContactosAdapter(this,listapersonajes)
        recycler.setAdapter(adapter)

    }


}