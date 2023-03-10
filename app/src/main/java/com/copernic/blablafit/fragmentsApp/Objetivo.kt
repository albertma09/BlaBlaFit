package com.copernic.blablafit.fragmentsApp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.copernic.blablafit.R
import com.copernic.blablafit.databinding.FragmentDatosFisicosBinding
import com.copernic.blablafit.databinding.FragmentObjetivoBinding
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
 * Use the [Objetivo.newInstance] factory method to
 * create an instance of this fragment.
 */
class Objetivo : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var _binding : FragmentObjetivoBinding
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    /**

    Infla el diseño para este fragmento y establece un oyente de clic en un botón.
    Al hacer clic, llama a una función para agregar un objetivo en la base de datos de Firebase.
    @param inflater El LayoutInflater utilizado para inflar el diseño del fragmento.
    @param container El contenedor opcional al que se agregará el fragmento.
    @param savedInstanceState El estado opcional del fragmento guardado previamente.
    @return La vista del fragmento inflado.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentObjetivoBinding.inflate(layoutInflater)


        val view = binding.root
        return view
    }
    /**

    Establece un oyente de clic en un botón y se conecta a la base de datos de Firebase.
    @param view La vista del fragmento que ha sido creada.
    @param savedInstanceState El estado opcional del fragmento guardado previamente.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = Firebase.firestore
        binding.recuperar.setOnClickListener { afegirDades() }
    }
    /**

    Agrega un objetivo especificado en un campo de texto a la base de datos de Firebase.
     */
    private fun afegirDades(){
        val objetivo = binding.textInputEditText2.text.toString()
        auth = Firebase.auth
        val refUser = db.collection("usuarios").document(auth.uid.toString())
        refUser.update("lista_objetivos",objetivo)
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Objetivo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Objetivo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}