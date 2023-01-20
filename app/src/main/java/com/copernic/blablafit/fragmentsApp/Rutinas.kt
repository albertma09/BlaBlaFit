package com.copernic.blablafit.fragmentsApp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.copernic.blablafit.R
import com.copernic.blablafit.databinding.FragmentPrincipalBinding
import com.copernic.blablafit.databinding.FragmentRutinasBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Rutinas.newInstance] factory method to
 * create an instance of this fragment.
 */
class Rutinas : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentRutinasBinding? = null
    private val binding get() = _binding!!
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
        _binding = FragmentRutinasBinding.inflate(inflater, container, false)
        val root: View = binding.root
        // Inflate the layout for this fragment
        binding.ganarMasa.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_rutinas_to_rutinas2)
        }
        binding.perderGrasa.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_rutinas_to_rutinas2)
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
         * @return A new instance of fragment Rutinas.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Rutinas().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}