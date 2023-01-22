package com.copernic.blablafit.fragmentsApp

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager

import com.copernic.blablafit.activities.ContactoModel
import com.copernic.blablafit.activities.ContactosAdapter
import com.copernic.blablafit.databinding.FragmentRutinas4Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class rutinas4 : Fragment() {
    // TODO: Rename and change types of parameters
    val args : rutinas4Args by navArgs()
    private var param1: String? = null
    private var param2: String? = null
    private var dato: ArrayList<ContactoModel>? = null
    private var tamaño : Int?= null
    private lateinit var _binding: FragmentRutinas4Binding
    private val binding get() = _binding!!
    private val myAdapter: ContactosAdapter = ContactosAdapter()
    val db = Firebase.firestore
    private var storage = FirebaseStorage.getInstance()
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
        _binding = FragmentRutinas4Binding.inflate(layoutInflater)
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


        myAdapter.ContactosAdapter(getRutinas())


        binding.recycler.adapter = myAdapter

    }
    /**

    Método encargado de obtener las rutinas de un usuario específico desde la base de datos.
    Utiliza la información del usuario actualmente autenticado para obtener su rutina.
    Utiliza una lista mutable para almacenar las rutinas obtenidas.
    Utiliza un día específico para obtener los ejercicios correspondientes.
    Descarga las imágenes de los ejercicios de Firebase Storage.
    @return una lista mutable de objetos ContactoModel que representan las rutinas del usuario.
     */
    @SuppressLint("SuspiciousIndentation")
    private fun getRutinas(): MutableList<ContactoModel> {
        val rutinas: MutableList<ContactoModel> = arrayListOf()
        val dia = args.dia
        db.collection("usuarios").document(auth.uid.toString()).get().addOnSuccessListener { user->
            var documento = user.get("rutina") as String
            println(documento)
            db.collection("Rutinas").document(documento).collection("rutina").document(dia).get()
                .addOnSuccessListener {
                    println(documento)
                    dato = it.get("Ejercicios") as ArrayList <ContactoModel>
                    tamaño = dato!!.size
                    var cuenta= 0
                    while(cuenta<tamaño!!){
                        val obj2 = dato!![cuenta] as HashMap<*,ContactoModel>
                        val reference = storage.getReference("ejercicios/${user.get("lugar")}/${obj2["img"]}")
                            reference.downloadUrl.addOnSuccessListener {
                            println("$cuenta      $it")
                                rutinas.add(
                                    ContactoModel(
                                        "${obj2["nombre"].toString()}",
                                        "Series: ${obj2["series"].toString()}",
                                        "Repeticiones: ${obj2["repeticiones"].toString()}",
                                        "$it"
                                    )
                                )
                        }



                        cuenta++
                    }


                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents: ", exception)
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
