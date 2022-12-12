package com.example.blablafit.fragmentsApp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blablafit.R
import com.example.blablafit.activities.ContactoModel
import com.example.blablafit.activities.ContactosAdapter
import com.example.blablafit.databinding.ActivityRecyclerViewBinding
import com.example.blablafit.databinding.FragmentRutinas3Binding
import com.example.blablafit.databinding.FragmentRutinas4Binding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class rutinas4 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var _binding: FragmentRutinas4Binding
    private val binding get() = _binding!!
    private val myAdapter: ContactosAdapter = ContactosAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRutinas4Binding.inflate(layoutInflater)
        _binding.recycler.visibility = View.GONE
        _binding.shimmerLayout.visibility = View.VISIBLE
        binding.shimmerLayout.startShimmer()
        Handler(Looper.getMainLooper()).postDelayed({

            binding.shimmerLayout.stopShimmer()
            binding.recycler.visibility = View.VISIBLE
            binding.shimmerLayout.visibility = View.GONE
        }, 5000)
        setupRecyclerView()
        val root = binding.root

        return root
        //return inflater.inflate(R.layout.fragment_rutinas4, container, false)
    }

    private fun setupRecyclerView() {

        binding.recycler.setHasFixedSize(true)


        binding.recycler.layoutManager = LinearLayoutManager(context)


        myAdapter.ContactosAdapter(getAnimals())


        binding.recycler.adapter = myAdapter

    }

    private fun getAnimals(): MutableList<ContactoModel> {
        val animals: MutableList<ContactoModel> = arrayListOf()
        animals.add(
            ContactoModel(
                "Eriçó",
                "Erinaceinae",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6jimbnhKGIjk--l0ovAaI4qVdLXFo3CJDhA&usqp=CAU"
            )
        )
        animals.add(
            ContactoModel(
                "Gat",
                "Felis catus",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTdG0gms0eSr1cF0XnKqOObx_mAKOMP0Hez3g&usqp=CAU"
            )
        )
        animals.add(
            ContactoModel(
                "Cisne",
                "Cygnus olor",
                "https://www.faunia.es/content/dam/fau/images/descubre-faunia/planea-tu-visita/animales/aves/cisne-negro/Cisne-negro-Animales-Faunia-main.jpg"
            )
        )
        animals.add(
            ContactoModel(
                "Gos",
                "Canis lupus familiaris",
                "https://static3.lasprovincias.es/www/multimedia/202110/29/media/cortadas/perro-kSgG-U1509933224883BG-624x385@Las%20Provincias.jpg"
            )
        )
        animals.add(
            ContactoModel(
                "Tigre",
                "Phantera tigris",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/54/Tigress_at_Jim_Corbett_National_Park.jpg/250px-Tigress_at_Jim_Corbett_National_Park.jpg"
            )
        )
        animals.add(
            ContactoModel(
                "ovella",
                "Ovis aries",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Flock_of_sheep.jpg/245px-Flock_of_sheep.jpg"
            )
        )
        animals.add(
            ContactoModel(
                "pardal",
                "Passer domesticus",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/HouseSparrow23.jpg/245px-HouseSparrow23.jpg"
            )
        )

        return animals

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment rutinas4.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            rutinas4().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
