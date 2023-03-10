package com.copernic.blablafit.fragmentsApp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.copernic.blablafit.R
import com.copernic.blablafit.activities.MainApp
import com.copernic.blablafit.databinding.ActivityRegistroBinding
import com.copernic.blablafit.databinding.FragmentDatosFisicosBinding
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
    private var genero: String? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var _binding: FragmentDatosFisicosBinding
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

    /**

    En este método se inicializan las vistas y se establecen los listeners para los botones de género y para el botón de guardar.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = Firebase.firestore
        binding.logoHombre.setOnClickListener { hombre() }
        binding.logoMujer.setOnClickListener { mujer() }
        binding.recuperar.setOnClickListener { afegirDades() }
    }

    /**

    En este método se asigna el valor "hombre" a la variable genero.
     */
    fun hombre() {
        genero = "hombre"
    }

    /**

    En este método se asigna el valor "mujer" a la variable genero.
     */
    fun mujer() {
        genero = "mujer"
    }

    /**

    En este método se recuperan los valores de altura, peso y edad del usuario y se actualizan en la base de datos de Firebase.
     */
    fun afegirDades() {

        val altura = binding.altura.text.toString()
        val peso = binding.peso.text.toString()
        val edad = binding.edad.text.toString()
        auth = Firebase.auth
        val refUser = db.collection("usuarios").document(auth.uid.toString())
        refUser.update("altura", altura)
        refUser.update("genero", genero)
        refUser.update("edad", edad)
        refUser.update("lista_peso", peso)

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