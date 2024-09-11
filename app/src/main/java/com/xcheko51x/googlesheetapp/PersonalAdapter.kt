package com.xcheko51x.googlesheetapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonalAdapter(
    val listaPersonal: List<Personal>
): RecyclerView.Adapter<PersonalAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvId: TextView = itemView.findViewById(R.id.tvId)
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvCorreo: TextView = itemView.findViewById(R.id.tvCorreo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonalAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_personal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonalAdapter.ViewHolder, position: Int) {
        val personal = listaPersonal[position]

        holder.tvId.text = personal.ID
        holder.tvNombre.text = personal.NOMBRE
        holder.tvCorreo.text = personal.CORREO
    }

    override fun getItemCount(): Int {
        return listaPersonal.size
    }

}