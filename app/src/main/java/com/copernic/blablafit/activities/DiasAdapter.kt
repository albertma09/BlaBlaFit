package com.copernic.blablafit.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.copernic.blablafit.databinding.CardView2Binding

import com.copernic.blablafit.databinding.CardViewBinding
import com.copernic.blablafit.fragmentsApp.rutinas4Directions
import com.copernic.blablafit.fragmentsApp.rutinaseleccionadaDirections
import com.squareup.picasso.Picasso


class DiasAdapter : RecyclerView.Adapter<DiasAdapter.ViewHolder>() {
    var dias: MutableList<DiasModel> = ArrayList()


    //Constructor
    fun DiasAdapter(dias: MutableList<DiasModel>) {
        this.dias = dias

    }

    //Inflar diseño
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            CardView2Binding.inflate(
                layoutInflater, parent, false
            )
        )
    }

    //Ligar datos
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {
            holder.itemView.setOnClickListener(){

            }
            with(dias.get(position)) {

                binding.contactName.text= this.dia


            }

        }

        val item = dias[position]
        holder.itemView.setOnClickListener {

            val dia : String? = item.DiasName

            val action = rutinaseleccionadaDirections.actionRutinaseleccionadaToRutinas4(dia!!)
            it.findNavController().navigate(action)

        }
    }

    //devolver cantidad de elementos
    override fun getItemCount(): Int {
        return dias.size
    }




    //clase anidada
    inner class ViewHolder(val binding: CardView2Binding): RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.cardViewAnimal.setOnClickListener{

            }
        }

    }


}