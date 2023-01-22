package com.copernic.blablafit.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.copernic.blablafit.databinding.CardViewBinding

import com.copernic.blablafit.fragmentsApp.rutinas4Directions


import com.squareup.picasso.Picasso


class ContactosAdapter : RecyclerView.Adapter<ContactosAdapter.ViewHolder>() {
    var contactos: MutableList<ContactoModel> = ArrayList()
    //lateinit var context: Context


    fun ContactosAdapter(contactos: MutableList<ContactoModel>) {
        this.contactos = contactos
        //this.context = contxt
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            CardViewBinding.inflate(
                layoutInflater, parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {
            holder.itemView.setOnClickListener(){
                println("GGGG")
            }
            with(contactos.get(position)) {

                binding.contactName.text= this.name
                binding.series.text = this.series
                binding.description1.text = this.repeticiones
                Picasso.get().load(this.contactImage).into(binding.imageView7)

            }

        }

        val item = contactos.get(position)

        holder.itemView.setOnClickListener {

            val imageURL : String? = item.contactImage
            val nombre : String? = item.contactName
            val action = rutinas4Directions.actionRutinas4ToDatos(nombre!!, imageURL!!)
            it.findNavController().navigate(action)
            //Toast.makeText(ge,contactos.get(position).contactName, Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return contactos.size
    }

    inner class ViewHolder(val binding: CardViewBinding ): RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.cardViewAnimal.setOnClickListener{

            }
        }

    }


}