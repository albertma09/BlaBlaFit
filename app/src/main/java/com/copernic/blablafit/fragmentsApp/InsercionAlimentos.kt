package com.copernic.blablafit.fragmentsApp

import android.content.ContentValues
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.copernic.blablafit.databinding.FragmentInsercionAlimentosBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.zxing.integration.android.IntentIntegrator
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InsercionAlimentos.newInstance] factory method to
 * create an instance of this fragment.
 */
class InsercionAlimentos : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var _binding: FragmentInsercionAlimentosBinding
    private lateinit var prueba: ArrayAdapter<String>
    val arr = arrayListOf<String>()
    private lateinit var auth: FirebaseAuth

    private val binding get() = _binding!!
    private val db = Firebase.firestore
    private val alimentacion = db.collection("Alimentacion")
    /**

    Inicializa el escáner QR y establece las configuraciones deseadas.
    Se establecen los formatos de código de barras deseados, la orientación del dispositivo, el texto de la pantalla de promoción, el uso de la linterna y el sonido al escanear.
     */

    private fun initScanner() {
        val integrator = IntentIntegrator.forSupportFragment(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setOrientationLocked(true)
        integrator.setPrompt("Prueba escaneo")
        for (i in 1..5) {
            integrator.setTorchEnabled(true)
            integrator.setTorchEnabled(false)

        }
        integrator.setTorchEnabled(false)
        integrator.setBeepEnabled(false)
        integrator.initiateScan()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    /**

    Infla el diseño para este fragmento y establece un oyente de clic en un botón de la cámara.
    Al hacer clic, llama a una función para inicializar el escáner QR.
    @param inflater El LayoutInflater utilizado para inflar el diseño del fragmento.
    @param container El contenedor opcional al que se agregará el fragmento.
    @param savedInstanceState El estado opcional del fragmento guardado previamente.
    @return La vista del fragmento inflado.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInsercionAlimentosBinding.inflate(layoutInflater)
        val view = binding.root
        _binding.camara.setOnClickListener {
            initScanner()
        }


        return view
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_insercion_alimentos, container, false)
    }

    /**

    Se conecta a la base de datos de Firebase y obtiene una lista de alimentos.

    Establece un adaptador para un spinner con la lista de alimentos y establece un oyente de clic en un botón "guardar".

    Al hacer clic, se valida que la cantidad ingresada sea numérica y se agrega la información de alimento seleccionado y cantidad a la base de datos de Firebase.

    @param view La vista del fragmento que ha sido creada.

    @param savedInstanceState El estado opcional del fragmento guardado previamente.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        db.collection("Alimentacion").get().addOnSuccessListener { documents ->

            for (document in documents) {
                arr.add(document.id)
            }
            prueba = ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arr
            )

            var spinner = _binding.spinner
            spinner.adapter = prueba

            auth = Firebase.auth
            _binding.guardar.setOnClickListener {
                val cadena = _binding.gramos.text.toString()
                var valida = true
                for (i: Int in 0 until cadena.length) {
                    val c: Char = cadena[i]

                    if (c in '0'..'9') {
                    } else {
                        valida = false
                        break
                    }
                }

                if (valida) {
                    val datetime =
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMddyyyy"))
                    db.collection("usuarios").document(auth.uid.toString()).collection("alimentos")
                        .get().addOnSuccessListener { documents2 ->
                            var creado = false
                            println("documents2:$documents2")
                            for (document2 in documents2) {

                                if (document2.id.equals(datetime)) {
                                    creado = true
                                    break
                                }
                            }
                            if (creado == false) {
                                val alimento = hashMapOf(
                                    "alimentos" to listOf(
                                        hashMapOf(
                                            "nombre" to spinner.selectedItem.toString(),
                                            "cantidad" to _binding.gramos.text.toString(),
                                        )
                                    )
                                )
                                db.collection("usuarios").document(auth.uid.toString())
                                    .collection("alimentos").document(datetime).set(alimento)
                            } else {
                                db.collection("usuarios").document(auth.uid.toString())
                                    .collection("alimentos").document(datetime).update(
                                        "alimentos", FieldValue.arrayUnion(
                                            hashMapOf(
                                                "nombre" to spinner.selectedItem.toString(),
                                                "cantidad" to _binding.gramos.text.toString(),
                                            )
                                        )
                                    )
                            }
                        }
                } else {
                    Snackbar.make(
                        _binding.snackbar,
                        "El valor introducido no es numerico",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }


            }
        }.addOnFailureListener { exception ->
            Log.w(ContentValues.TAG, "Error getting documents: ", exception)
        }
    }
    /**

    Maneja el resultado de la actividad del escáner QR. Si el resultado es válido, muestra un mensaje Toast con el valor escaneado, y llama a una función para obtener un valor de cantidad.

    Luego, separa la información del código QR en un mapa de alimentos, y agrega esa información a la base de datos de Firebase.

    @param requestCode El código de solicitud utilizado para iniciar la actividad de escaneo.

    @param resultCode El código de resultado devuelto por la actividad de escaneo.

    @param data El intent opcional que contiene los datos devueltos por la actividad de escaneo.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        var qty = 0
        if (result != null) {
            if (result.contents != null) {

                Toast.makeText(
                    activity,
                    "El valor escaneado es: " + result.contents,
                    Toast.LENGTH_LONG
                ).show()
                qty = inputValue()
            }
        }
        val alimentos = mutableMapOf<String, String>()
        var contenido = result.contents.split("|")
        var producto = contenido[0].split(":")[1]

        for (i in 1 until contenido.size) {
            val key = contenido[i].split(":")[0]
            val value = contenido[i].split(":")[1]
            alimentos[key] = value

        }
        alimentacion.document(producto).set(alimentos)

    }
    /**

    Obtiene un valor de cantidad de un cuadro de diálogo de alerta.

    Muestra un cuadro de diálogo de alerta que pide al usuario que ingrese una cantidad, y devuelve ese valor como un entero.

    @return la cantidad ingresada por el usuario como un entero.
     */
    fun inputValue(): Int {
        var cantidad = 0
        val input = EditText(activity)
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Ejemplo")
        builder.setTitle("Introducir cantidad")
        builder.setMessage("Qty")

        builder.setView(input)
        builder.setPositiveButton("OK") { _, _ ->
            cantidad = Integer.parseInt(input.text.toString())
            Toast.makeText(activity, "La cantidad es ${cantidad} ", Toast.LENGTH_LONG)
                .show()

        }
        builder.setNegativeButton("Cancelar") { _, _ ->

        }

// Set up the buttons


        builder.show()
        return cantidad
    }


}

