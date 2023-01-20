package com.copernic.blablafit.fragmentsApp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.copernic.blablafit.R
import com.copernic.blablafit.databinding.FragmentRutinas2Binding
import com.copernic.blablafit.databinding.FragmentRutinas3Binding
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
 * Use the [Rutinas3.newInstance] factory method to
 * create an instance of this fragment.
 */
class Rutinas3 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentRutinas3Binding? = null
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
        _binding = FragmentRutinas3Binding.inflate(inflater, container, false)
        val root: View = binding.root
        // Inflate the layout for this fragment
        binding.casa.setOnClickListener{nav->

            db.collection("usuarios").document(auth.uid.toString()).update("lugar","casa").addOnSuccessListener {
                rutina(nav)
            }


        }
        binding.gym.setOnClickListener{nav->

            db.collection("usuarios").document(auth.uid.toString()).update("lugar","gimnasio").addOnSuccessListener {
                rutina(nav)
            }

        }


        return root
    }

    fun rutina(nav:View){
        db.collection("usuarios").document(auth.uid.toString()).get().addOnSuccessListener {
            var userDias = it.get("entrenoSemanal") as Long
            var userLugar = it.get("lugar") as String

            db.collection("Rutinas").get().addOnSuccessListener { documents->
                var dias : Long
                var lugar : String
                for (document in documents){
                    dias = document.get("dias") as Long
                    lugar = document.get("lugar") as String

                    if (userDias==dias&&userLugar.equals(lugar)){
                        db.collection("usuarios").document(auth.uid.toString()).update("rutina",document.id).addOnSuccessListener {
                            Navigation.findNavController(nav).navigate(R.id.action_rutinas3_to_rutinaseleccionada3)
                        }
                    }
                }

            }
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Rutinas3.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Rutinas3().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}