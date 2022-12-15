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
import com.example.blablafit.activities.MainApp
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
    var progres : Int = 0
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




            val i : ProgressBar = binding.indicador

            progres = i.progress + 10
            if( progres > i.max){

                progres = 0
                i.progress = 0
            }else{
                i.progress = progres
            }
            println(i.progress )
            //if(i.progress == 100){
             //   i.progress = 0
            //}

        }

    }


