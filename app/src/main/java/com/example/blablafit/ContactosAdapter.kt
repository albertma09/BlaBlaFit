package com.example.blablafit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.blablafit.databinding.ActivityRecyclerViewBinding
import com.example.blablafit.databinding.CardViewBinding
import com.squareup.picasso.Picasso


class ContactosAdapter : RecyclerView.Adapter<ContactosAdapter.ViewHolder>() {
    var contactos: MutableList<ContactoModel> = ArrayList()
    lateinit var context: Context

    //constructor de la classe on es passa la font de dades i el context sobre el que es mostrarà
    fun ContactosAdapter(contactos: MutableList<ContactoModel>, contxt: Context,) {
        this.contactos = contactos
        this.context = contxt
    }

    //és l'encarregat de retornar el ViewHolder ja configurat
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            CardViewBinding.inflate(
                layoutInflater, parent, false
            )
        )
    }

    //Aquest mètode s'encarrega de passar els objectes, un a un al ViewHolder personalitzat
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {
            with(contactos.get(position)) {

                binding.contactName.text= this.name
                binding.description1.text = this.description
                Picasso.get().load(this.contactImage).into(binding.imageView7)

                //binding.= this.latinName
                //binding.imgAnimal.load(this.imageAnimal)
                /*
                 //Monstrar la imatge des de Storage de Firebase
                 val storageRef = FirebaseStorage.getInstance().reference
                 val imageRef = storageRef.child("rv/${this.animalName}")
                 imageRef.downloadUrl.addOnSuccessListener { url ->
                     binding.imgAnimal.load(url)
                 }.addOnFailureListener {
                     //mostrar error
                 } */
            }
        }
        val item = contactos.get(position)
        holder.bind(item)

        //estamblim un listener
        holder.itemView.setOnClickListener {
            Toast.makeText(context, contactos.get(position).contactName, Toast.LENGTH_LONG).show()
        }
    }


    override fun getItemCount(): Int {
        return contactos.size
    }


    class ViewHolder(val binding: CardViewBinding ): RecyclerView.ViewHolder(binding.root) {

        fun bind(contacto: ContactoModel) {

        }

    }


}