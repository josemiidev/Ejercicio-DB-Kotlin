package com.example.ejerciciodbkotlin.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejerciciodbkotlin.UI.AdminSQLiteOpenHelper
import com.example.ejerciciodbkotlin.UI.Alumno
import com.example.ejerciciodbkotlin.UI.AlumnoAdapter
import com.example.ejerciciodbkotlin.databinding.FragmentListarBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ListarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListarFragment : Fragment() {
    private lateinit var binding: FragmentListarBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListarBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val admin = AdminSQLiteOpenHelper(view.context, "escuela", null, 1)
        val BaseDeDatos = admin.readableDatabase
        val cursor = BaseDeDatos.rawQuery("select * from alumnos",null)
        var lista = ArrayList<Alumno>()

        if(cursor.moveToFirst()){
            do {
                val dni = cursor.getString(0)
                val nombre = cursor.getString(1)
                val apellidos = cursor.getString(2)
                val sexo = cursor.getString(3)
                lista.add(Alumno(dni,nombre,apellidos,sexo))
            }while(cursor.moveToNext())
        }
        binding.rvListado.layoutManager = LinearLayoutManager(view.context)
        //pasar datos desde sqlite
        binding.rvListado.adapter = AlumnoAdapter(lista)

        binding.rvListado.setOnClickListener {
        }
    }
}