package com.example.blablafit.fragmentsApp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class rutinas4 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var dato: ArrayList<ContactoModel>? = null
    private var tamaño : Int?= null
    private lateinit var _binding: FragmentRutinas4Binding
    private val binding get() = _binding!!
    private val myAdapter: ContactosAdapter = ContactosAdapter()
    val db = Firebase.firestore
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
        val rutinas: MutableList<ContactoModel> = arrayListOf()

        db.collection("Rutinas").document("uGVdPT1Jll9XvxRTZsID").collection("rutina").document("Dia2").get()
            .addOnSuccessListener {
                dato = it.get("Ejercicios") as ArrayList <ContactoModel>
                tamaño = dato!!.size
                var cuenta= 0
                while(cuenta<tamaño!!){
                    val obj2 = dato!![cuenta] as HashMap<*,ContactoModel>

                    rutinas.add(
                        ContactoModel(
                            "${obj2["nombre"].toString()}",
                            "${obj2["series"].toString()}",
                            "${obj2["repeticiones"].toString()}",
                            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6jimbnhKGIjk--l0ovAaI4qVdLXFo3CJDhA&usqp=CAU"
                        )
                    )
                    cuenta++
                }


            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }


        return rutinas

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
