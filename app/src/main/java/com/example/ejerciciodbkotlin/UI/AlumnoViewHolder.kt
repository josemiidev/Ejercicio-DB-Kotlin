package com.example.ejerciciodbkotlin.UI

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.ejerciciodbkotlin.R
import com.example.ejerciciodbkotlin.databinding.ItemAlumnoBinding

class AlumnoViewHolder(view:View) : RecyclerView.ViewHolder(view){

    private val binding = ItemAlumnoBinding.bind(view)

    fun render(alumno: Alumno){
        binding.tvNombre.text = alumno.nombre
        binding.tvApellidos.text = alumno.apellidos
        binding.tvDni.text = alumno.dni

        if(alumno.sexo?.uppercase().equals("HOMBRE")){
            binding.ivAvatar.setImageResource(R.drawable.hombre)
        }else{
            binding.ivAvatar.setImageResource(R.drawable.mujer)
        }
    }
}