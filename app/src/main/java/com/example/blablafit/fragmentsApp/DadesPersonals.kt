package com.example.blablafit.fragmentsApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.blablafit.R
import com.example.blablafit.databinding.FragmentDadesPersonalsBinding
import com.google.firebase.storage.FirebaseStorage

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DadesPersonals.newInstance] factory method to
 * create an instance of this fragment.
 */
class DadesPersonals : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var _binding: FragmentDadesPersonalsBinding
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDadesPersonalsBinding.inflate(layoutInflater)
        val view = binding.root
        _binding.agua.setOnClickListener { updateBar() }
        return view
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_dades_personals, container, false)
    }

    fun updateBar(){

       /* val i : ProgressBar = findViewById(R.id.indicador)
        progres = i.progress+10
        i.progress= progres
        Toast.makeText(this,progres.toString(), Toast.LENGTH_LONG).show()
        if(i.progress == i.max){
            i.progress =0
            Toast.makeText(this,"Has bebido 2 litros", Toast.LENGTH_LONG).show()
        }*/

    }


}