package com.example.ejerciciodbkotlin.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ejerciciodbkotlin.R
import kotlin.coroutines.coroutineContext

class AlumnoAdapter(val listaAlumnos: List<Alumno>) : RecyclerView.Adapter<AlumnoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlumnoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AlumnoViewHolder(layoutInflater.inflate(R.layout.item_alumno,parent,false))
    }

    override fun onBindViewHolder(holder: AlumnoViewHolder, position: Int) {
        val item = listaAlumnos[position]
        holder.render(item)
        holder.itemView.setOnClickListener{
            var bundle = Bundle()
            bundle.putString("dni", item.dni)
            bundle.putString("nombre", item.nombre)
            bundle.putString("apellidos", item.apellidos)
            bundle.putString("sexo", item.sexo)
            it?.findNavController()?.navigate(R.id.nav_modificar, bundle)
        }
    }

    override fun getItemCount(): Int = listaAlumnos.size

}