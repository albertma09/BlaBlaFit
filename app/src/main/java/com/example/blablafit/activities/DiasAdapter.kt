package com.example.blablafit.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.blablafit.databinding.CardView2Binding
import com.example.blablafit.databinding.CardViewBinding
import com.example.blablafit.fragmentsApp.rutinas4Directions
import com.example.blablafit.fragmentsApp.rutinaseleccionadaDirections
import com.squareup.picasso.Picasso


class DiasAdapter : RecyclerView.Adapter<DiasAdapter.ViewHolder>() {
    var dias: MutableList<DiasModel> = ArrayList()
    //lateinit var context: Context


    fun DiasAdapter(dias: MutableList<DiasModel>) {
        this.dias = dias
        //this.context = contxt
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            CardView2Binding.inflate(
                layoutInflater, parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {
            holder.itemView.setOnClickListener(){
                println("GGGG")
            }
            with(dias.get(position)) {

                binding.contactName.text= this.dia


            }

        }

        val item = dias.get(position)




        holder.itemView.setOnClickListener {

            val dia : String? = item.DiasName

            val action = rutinaseleccionadaDirections.actionRutinaseleccionadaToRutinas4(dia!!)
            it.findNavController().navigate(action)
            //Toast.makeText(ge,contactos.get(position).contactName, Toast.LENGTH_LONG).show()
        }
    }


    override fun getItemCount(): Int {
        return dias.size
    }





    inner class ViewHolder(val binding: CardView2Binding ): RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.cardViewAnimal.setOnClickListener{
                println("Hola")
            }
        }

    }


}