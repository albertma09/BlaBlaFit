package com.copernic.blablafit.fragmentsApp

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.copernic.blablafit.activities.Alimentacion
import com.copernic.blablafit.activities.ContactoModel
import com.copernic.blablafit.databinding.FragmentDadesPersonalsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


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
    val db = Firebase.firestore
    private lateinit var auth: FirebaseAuth
    var caloriasTotat : Float = 0F
    var proteinasTotat : Float = 0F
    var carbohidratosTotat : Float = 0F
    var grasasTotat : Float = 0F
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDadesPersonalsBinding.inflate(layoutInflater)







        _binding.agua.setOnClickListener { updateBar() }
        val view = binding.root
        return view
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_dades_personals, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val datetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMddyyyy"))
        auth = Firebase.auth

            db.collection("usuarios").document(auth.uid.toString()).collection("alimentos").document(datetime).get()
                .addOnSuccessListener {
                    var alimento:ArrayList<Alimentacion> = ArrayList()
                    try{
                    alimento = it.get("alimentos") as ArrayList<Alimentacion>
                        var tamaño = alimento.size
                        var cuenta= 0
                        println("$alimento")

                        db.collection("Alimentacion").get().addOnSuccessListener {
                                documents->
                            while(cuenta<tamaño!!) {
                                val obj2 = alimento!![cuenta] as HashMap<*, Alimentacion>
                                val cantidad = Integer.parseInt(obj2["cantidad"].toString())
                                val nombre = obj2["nombre"].toString()
                                println("$nombre")
                                for (document in documents){
                                    println(document.id)
                                    if ((document.id).equals(nombre)){
                                        println("fdfksdnfjsdnfsd")
                                        var calorias = document.get("calorias").toString().toFloat()*cantidad/100
                                        var proteinas = document.get("proteinas").toString().toFloat()*cantidad/100
                                        var carbohidratos = document.get("carbohidratos").toString().toFloat()*cantidad/100
                                        var grasas = document.get("grasas").toString().toFloat()*cantidad/100
                                        caloriasTotat += calorias
                                        proteinasTotat += proteinas
                                        carbohidratosTotat += carbohidratos
                                        grasasTotat += grasas
                                    }

                                }
                                cuenta++
                            }
                            _binding.piechart.addPieSlice(
                                PieModel(
                                    "Calorias", caloriasTotat,
                                    Color.parseColor("#FFA726")
                                )
                            )
                            _binding.piechart.addPieSlice(
                                PieModel(
                                    "Proteinas", proteinasTotat,
                                    Color.parseColor("#66BB6A")
                                )
                            )
                            _binding.piechart.addPieSlice(
                                PieModel(
                                    "Carbohidratos", carbohidratosTotat,
                                    Color.parseColor("#EF5350")
                                )
                            )
                            _binding.piechart.addPieSlice(
                                PieModel(
                                    "Grasas", grasasTotat,
                                    Color.parseColor("#29B6F6")
                                )
                            )
                        }
                }catch (e:java.lang.NullPointerException){
                    Toast.makeText(activity, "tienes que añadir un alimento para cargar la grafica",Toast.LENGTH_LONG).show()
                }

                    }
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


