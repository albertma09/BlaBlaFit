package com.example.blablafit.fragmentsApp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.blablafit.R
import com.example.blablafit.databinding.FragmentPrincipalBinding
import com.google.android.material.navigation.NavigationView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Principal.newInstance] factory method to
 * create an instance of this fragment.
 */
class Principal : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentPrincipalBinding? = null
    private val binding get() = _binding!!
    private lateinit var navView: NavigationView
    private lateinit var  navController : NavController

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
        //navView =
        _binding = FragmentPrincipalBinding.inflate(inflater, container, false)
        val root: View = binding.root



//        val navHostFragment = parentFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
  //      val navController = navHostFragment.navController

        val view = inflater.inflate(R.layout.fragment_principal,container,false)
        binding.dietas.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_principal_to_dietas)
        }

        binding.rutinasMenu.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_principal_to_rutinas)
        }

        binding.alimentacion.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_principal_to_insercionAlimentos)
        }
        // Inflate the layout for this fragment
        return root
        
        
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Principal.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Principal().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}