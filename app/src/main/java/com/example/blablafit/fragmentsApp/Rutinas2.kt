package com.example.blablafit.fragmentsApp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.blablafit.R
import com.example.blablafit.databinding.FragmentRutinas2Binding
import com.example.blablafit.databinding.FragmentRutinasBinding
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
 * Use the [Rutinas2.newInstance] factory method to
 * create an instance of this fragment.
 */
class Rutinas2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentRutinas2Binding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Firebase.firestore
        auth = Firebase.auth

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRutinas2Binding.inflate(inflater, container, false)
        val root: View = binding.root
        // Inflate the layout for this fragment
        binding.dias3.setOnClickListener{

            db.collection("usuarios").document(auth.uid.toString()).update("entrenoSemanal",3)
            Navigation.findNavController(it).navigate(R.id.action_rutinas2_to_rutinas3)
        }
        binding.dias4.setOnClickListener{


            db.collection("usuarios").document(auth.uid.toString()).update("entrenoSemanal",4)
            Navigation.findNavController(it).navigate(R.id.action_rutinas2_to_rutinas3)
        }
        binding.dias5.setOnClickListener{

            db.collection("usuarios").document(auth.uid.toString()).update("entrenoSemanal",5)
            Navigation.findNavController(it).navigate(R.id.action_rutinas2_to_rutinas3)
        }
        binding.dias6.setOnClickListener{

            db.collection("usuarios").document(auth.uid.toString()).update("entrenoSemanal",6)
            Navigation.findNavController(it).navigate(R.id.action_rutinas2_to_rutinas3)
        }


        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Rutinas2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Rutinas2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}