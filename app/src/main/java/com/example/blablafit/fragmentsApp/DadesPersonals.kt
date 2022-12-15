package com.example.blablafit.fragmentsApp

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.example.blablafit.databinding.FragmentDadesPersonalsBinding
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel





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
    var pieChart: PieChart? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDadesPersonalsBinding.inflate(layoutInflater)
        _binding.piechart.addPieSlice(
            PieModel(
                "Calorias", 600F,
                Color.parseColor("#FFA726")
            )
        )
        _binding.piechart.addPieSlice(
            PieModel(
                "Proteinas", 20F,
                Color.parseColor("#66BB6A")
            )
        )
        _binding.piechart.addPieSlice(
            PieModel(
                "Carbohidratos", 350F,
                Color.parseColor("#EF5350")
            )
        )
        _binding.piechart.addPieSlice(
            PieModel(
                "Grasas", 100F,
                Color.parseColor("#29B6F6")
            )
        )
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


