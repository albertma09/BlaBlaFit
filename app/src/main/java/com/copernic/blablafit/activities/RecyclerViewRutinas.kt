package com.copernic.blablafit.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.copernic.blablafit.databinding.ActivityRecyclerViewBinding

class RecyclerViewRutinas : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewBinding

    private val myAdapter: ContactosAdapter = ContactosAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recycler.visibility = View.GONE
        binding.shimmerLayout.visibility = View.VISIBLE
        binding.shimmerLayout.startShimmer()
        Handler(Looper.getMainLooper()).postDelayed({

            binding.shimmerLayout.stopShimmer()
            binding.recycler.visibility = View.VISIBLE
            binding.shimmerLayout.visibility = View.GONE
        }, 5000)
        setupRecyclerView()
    }

    private fun setupRecyclerView(){

        binding.recycler.setHasFixedSize(true)


        binding.recycler.layoutManager = LinearLayoutManager(this)


        myAdapter.ContactosAdapter(getAnimals())


        binding.recycler.adapter = myAdapter
    }

    private fun getAnimals():MutableList<ContactoModel>{
        val animals: MutableList<ContactoModel> = arrayListOf()
        animals.add(ContactoModel("Eriçó", "Erinaceinae","prueba","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6jimbnhKGIjk--l0ovAaI4qVdLXFo3CJDhA&usqp=CAU"))
        animals.add(ContactoModel("Gat", "Felis catus","prueba","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTdG0gms0eSr1cF0XnKqOObx_mAKOMP0Hez3g&usqp=CAU"))
        animals.add(ContactoModel("Cisne", "Cygnus olor","prueba","https://www.faunia.es/content/dam/fau/images/descubre-faunia/planea-tu-visita/animales/aves/cisne-negro/Cisne-negro-Animales-Faunia-main.jpg"))
        animals.add(ContactoModel("Gos", "Canis lupus familiaris","prueba","https://static3.lasprovincias.es/www/multimedia/202110/29/media/cortadas/perro-kSgG-U1509933224883BG-624x385@Las%20Provincias.jpg"))
        animals.add(ContactoModel("Tigre", "Phantera tigris","prueba","https://upload.wikimedia.org/wikipedia/commons/thumb/5/54/Tigress_at_Jim_Corbett_National_Park.jpg/250px-Tigress_at_Jim_Corbett_National_Park.jpg"))
        animals.add(ContactoModel("ovella", "Ovis aries","prueba","https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Flock_of_sheep.jpg/245px-Flock_of_sheep.jpg"))
        animals.add(ContactoModel("pardal", "Passer domesticus","prueba","https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/HouseSparrow23.jpg/245px-HouseSparrow23.jpg"))

        return animals

    }




}