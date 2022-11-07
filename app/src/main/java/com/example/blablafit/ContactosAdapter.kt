package com.example.blablafit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactosAdapter(var context: Context, val contactos:MutableList<Contacto>)
    : RecyclerView.Adapter<ContactosAdapter.ViewHolder>() {

    class ViewHolder(v: View):RecyclerView.ViewHolder(v){
        var nombre:TextView
        init{
            nombre = v.findViewById(R.id.tvUserName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p = contactos[position]
        holder.nombre.text = p.nombre
    }

    override fun getItemCount(): Int {
        return contactos.size
    }
}