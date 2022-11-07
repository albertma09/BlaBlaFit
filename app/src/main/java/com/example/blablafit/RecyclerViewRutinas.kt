package com.example.blablafit

import ContactosAdapter
import android.R
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blablafit.databinding.ActivityRecyclerViewBinding

class RecyclerViewRutinas : AppCompatActivity() {

    private lateinit var binding:ActivityRecyclerViewBinding
    private  var listapersonajes:MutableList<ContactoModel> = mutableListOf()
    //private lateinit var recycler:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("carga datos: ","linea 23")
        listapersonajes.add(ContactoModel(("Naruto Uzumaki")))
        listapersonajes.add(ContactoModel(("Sasuke Uchiha")))
        listapersonajes.add(ContactoModel(("Personaje")))
        listapersonajes.add(ContactoModel(("Sakura Haruno")))
        listapersonajes.add(ContactoModel(("Sai")))
        listapersonajes.add(ContactoModel(("Hinata Hyūga")))
        listapersonajes.add(ContactoModel(("Kiba Inuzuka")))
        listapersonajes.add(ContactoModel(("Chōji Akimichi")))
        listapersonajes.add(ContactoModel(("Rock Lee")))
        Log.d("datos cargados: ","linea 33")
        Log.d("Recycler view lista","personajes: ${listapersonajes.count()}")
        // ...
        // Lookup the recyclerview in activity layout

        val rvContacts = binding.recycler
        // Initialize contacts
        val contacts = ContactoModel.createContactsList(20)
        // Create adapter passing in the sample user data
        Log.d("Recycler view rutinas","linea 41")
        val adapter = ContactosAdapter(listapersonajes)
        // Attach the adapter to the recyclerview to populate items
        Log.d("Recycler view rutinas","linea 43")
        rvContacts.adapter = adapter
        // Set layout manager to position the items
        rvContacts.layoutManager = LinearLayoutManager(this)
        // That's all!

        adapter.notifyItemInserted(0)


    }





}