package com.copernic.blablafit.fragmentsApp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.copernic.blablafit.R
import com.copernic.blablafit.activities.MainApp
import com.copernic.blablafit.databinding.FragmentDatosBinding
import com.copernic.blablafit.databinding.FragmentDatosFisicosBinding
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Datos.newInstance] factory method to
 * create an instance of this fragment.
 */
class Datos : Fragment() {
    // TODO: Rename and change types of parameters

    val args: DatosArgs by navArgs()
    private lateinit var _binding : FragmentDatosBinding
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Log.i("Log",args.puntos.toString())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDatosBinding.inflate(layoutInflater)


        val view = binding.root
        return view
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_datos, container, false)
    }
    /**

    Método que se ejecuta al crear la vista del fragmento.
    Obtiene los argumentos enviados desde la actividad, y los asigna a los elementos de la vista.
    @param view La vista del fragmento
    @param savedInstanceState El estado guardado de la instancia
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tv: TextView = view.findViewById(R.id.textViu)
        val nombre = args.nombre
        val imagen = args.imageURL
        Picasso.get().load(imagen).into(binding.imageView3)
        tv.text = nombre
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Datos.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Datos().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}