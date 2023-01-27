package com.example.ejerciciodbkotlin.Fragments

import android.content.ContentValues
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.ejerciciodbkotlin.R
import com.example.ejerciciodbkotlin.UI.AdminSQLiteOpenHelper
import com.example.ejerciciodbkotlin.databinding.FragmentMatricularBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MatricularFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MatricularFragment : Fragment() {

    private lateinit var binding: FragmentMatricularBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMatricularBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sexos = resources.getStringArray(R.array.sexo)

        val adapter = ArrayAdapter(
            view.context,
            android.R.layout.simple_spinner_item, sexos
        )
        binding.spSexoMatricula.adapter = adapter

        binding.btnAceptarMatricula.setOnClickListener {
            val dni = binding.etDniMatricula.text.toString()
            val nombre = binding.etNombreMatricula.text.toString()
            val apellidos = binding.etApellidosMatricula.text.toString()
            val sexo = binding.spSexoMatricula.selectedItem.toString()
            if (!dni.isNullOrEmpty() && !nombre.isNullOrEmpty() && !apellidos.isNullOrEmpty()) {
                val admin = AdminSQLiteOpenHelper(view.context, "escuela", null, 1)
                val BaseDeDatos = admin.writableDatabase

                val registro = ContentValues()

                registro.put("dni", dni)
                registro.put("nombre", nombre)
                registro.put("apellidos", apellidos)
                registro.put("sexo", sexo)

                BaseDeDatos.insert("alumnos", null, registro)

                BaseDeDatos.close()

                binding.etDniMatricula.text.clear()
                binding.etNombreMatricula.text.clear()
                binding.etApellidosMatricula.text.clear()

                binding.etDniMatricula.requestFocus()

            }
        }
    }
}