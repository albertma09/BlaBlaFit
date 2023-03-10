package com.copernic.blablafit.fragmentsApp

import android.content.ContentValues
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.copernic.blablafit.activities.DiasAdapter
import com.copernic.blablafit.activities.DiasModel
import com.copernic.blablafit.databinding.FragmentRutinaseleccionadaBinding

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [rutinaseleccionada.newInstance] factory method to
 * create an instance of this fragment.
 */
class rutinaseleccionada : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var dato: ArrayList<DiasModel>? = null
    private var tamaño: Int? = null
    private lateinit var _binding: FragmentRutinaseleccionadaBinding
    private val binding get() = _binding!!
    private val myAdapter: DiasAdapter = DiasAdapter()
    val db = Firebase.firestore
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

    }

    /**

    Método encargado de crear la vista del fragmento. Infla el layout y configura el recyclerview.

    También se encarga de mostrar un efecto shimmer mientras se cargan los datos.

    @param inflater LayoutInflater necesario para inflar el layout

    @param container contenedor donde se va a mostrar la vista

    @param savedInstanceState estado guardado de la instancia

    @return la vista creada
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRutinaseleccionadaBinding.inflate(layoutInflater)
        _binding.recycler.visibility = View.GONE
        _binding.shimmerLayout.visibility = View.VISIBLE
        binding.shimmerLayout.startShimmer()
        Handler(Looper.getMainLooper()).postDelayed({

            binding.shimmerLayout.stopShimmer()
            binding.recycler.visibility = View.VISIBLE
            binding.shimmerLayout.visibility = View.GONE
        }, 1000)
        setupRecyclerView()
        val root = binding.root

        return root
        //return inflater.inflate(R.layout.fragment_rutinas4, container, false)
    }

    /**

    Método encargado de configurar el recyclerview.

    Establece el tamaño fijo, el layout manager y el adaptador.
     */
    private fun setupRecyclerView() {

        binding.recycler.setHasFixedSize(true)


        binding.recycler.layoutManager = LinearLayoutManager(context)


        myAdapter.DiasAdapter(getRutinas())


        binding.recycler.adapter = myAdapter

    }
    /**

    Método encargado de obtener las rutinas de un usuario específico desde la base de datos.
    Utiliza la información del usuario actualmente autenticado para obtener su rutina.
    Utiliza una lista mutable para almacenar las rutinas obtenidas.
    @return una lista mutable de objetos DiasModel que representan las rutinas del usuario.
     */
    private fun getRutinas(): MutableList<DiasModel> {
        val rutinas: MutableList<DiasModel> = arrayListOf()
        db.collection("usuarios").document(auth.uid.toString()).get().addOnSuccessListener {
            var documento = it.get("rutina") as String
            println(documento)
            db.collection("Rutinas").document(documento).collection("rutina").get()
                .addOnSuccessListener { documents ->
                    println(documento)
                    for (document in documents) {
                        rutinas.add(
                            DiasModel(
                                document.id,
                            )
                        )
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                }
        }



        return rutinas

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment rutinas4.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            rutinas4().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}