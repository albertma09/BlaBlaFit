package com.copernic.blablafit.fragmentsApp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import coil.api.load
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.copernic.blablafit.R
import com.copernic.blablafit.databinding.FragmentDatosFisicosBinding
import com.copernic.blablafit.databinding.FragmentPerfilPersonal2Binding
import com.copernic.blablafit.databinding.FragmentRutinas4Binding
import com.google.android.gms.common.internal.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PerfilPersonal2.newInstance] factory method to
 * create an instance of this fragment.
 */
class PerfilPersonal2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var _binding : FragmentPerfilPersonal2Binding
    lateinit var getContent : ActivityResultLauncher<String>
    private val binding get() = _binding!!
    private val db = Firebase.firestore
    private var storage = FirebaseStorage.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                managePhotoUri(uri)
            }
        }
    }
    /**

    Infla el diseño para este fragmento y establece un oyente de clic para una imagen de perfil.
    Al hacer clic, se llama a una función para agregar una imagen. También se carga información de la base de datos de Firebase y se muestra en la vista.
    @param inflater El LayoutInflater utilizado para inflar el diseño del fragmento.
    @param container El contenedor opcional al que se agregará el fragmento.
    @param savedInstanceState El estado opcional del fragmento guardado previamente.
    @return La vista del fragmento inflado.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPerfilPersonal2Binding.inflate(layoutInflater)
        val root: View = binding.root
        // Inflate the layout for this fragment
        return root
    }
    /**

    Establece un oyente de clic para la imagen de perfil y carga información de la base de datos de Firebase en la vista.
    @param view La vista del fragmento que ha sido creada.
    @param savedInstanceState El estado opcional del fragmento guardado previamente.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.FotoPerfil.setOnClickListener { afegirImatge() }
        auth = Firebase.auth
        val storage = FirebaseStorage.getInstance()
        val reference = storage.getReference("usersImages/${auth.uid.toString()}/perfil")
        db.collection("usuarios").document(auth.uid.toString()).get().addOnSuccessListener {
            binding.nombre.text =  it.get("nombre_usuario").toString()
            binding.correo.text =  it.get("email").toString()
        }
        reference.downloadUrl.addOnSuccessListener {
            binding.FotoPerfil.load(it)
            println(it)
        }

    }
    /**

    Maneja la URI de una imagen seleccionada y la carga en Firebase Storage.
    @param fileUri La URI de la imagen seleccionada.
     */
    fun managePhotoUri(fileUri : Uri){
        auth = Firebase.auth
        val sRef: StorageReference =
            storage.reference.child("usersImages/${auth.uid.toString()}/perfil")
        sRef.putFile(fileUri)


    }
/**

Inicia la selección de una imagen desde el almacenamiento del dispositivo.
*/
   fun afegirImatge(){
    getContent.launch("image/*")



    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PerfilPersonal2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PerfilPersonal2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}