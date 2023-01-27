package com.example.ejerciciodbkotlin.UI

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ejerciciodbkotlin.R

class AlumnoAdapter(val listaAlumnos: List<Alumno>) : RecyclerView.Adapter<AlumnoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlumnoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AlumnoViewHolder(layoutInflater.inflate(R.layout.item_alumno,parent,false))
    }

    override fun onBindViewHolder(holder: AlumnoViewHolder, position: Int) {
        val item = listaAlumnos[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = listaAlumnos.size
}