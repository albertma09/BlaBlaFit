package com.example.blablafit.fragmentsApp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.blablafit.R
import com.example.blablafit.activities.MainApp
import com.example.blablafit.databinding.ActivityRegistroBinding
import com.example.blablafit.databinding.FragmentDatosFisicosBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Datos_fiicos.newInstance] factory method to
 * create an instance of this fragment.
 */
class Datos_fisicos : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var genero:String? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var _binding : FragmentDatosFisicosBinding
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDatosFisicosBinding.inflate(layoutInflater)


        val view = binding.root
        return view
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_datos_fisicos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = Firebase.firestore
        binding.logoHombre.setOnClickListener{ hombre() }
        binding.logoMujer.setOnClickListener{ mujer() }
        binding.recuperar.setOnClickListener { afegirDades() }
    }
    fun hombre(){
        genero = "hombre"
    }
    fun mujer(){
        genero = "mujer"
    }
    fun afegirDades(){

        val altura = binding.altura.text.toString()
        val peso = binding.peso.text.toString()
        val edad = binding.edad.text.toString()
        auth = Firebase.auth
        val refUser = db.collection("usuarios").document(auth.uid.toString())
        refUser.update("altura",altura)
        refUser.update("genero",genero)
        refUser.update("edad",edad)
        refUser.update("lista_peso",peso)
        /*val usuario = hashMapOf(
            "Altura" to altura,
            "Peso" to peso,
            "Edad" to edad,
        )

         */

       // db.collection("usuarios").document(auth.uid.toString()).set(usuario)
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Datos_fiicos.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Datos_fisicos().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }


    }
}