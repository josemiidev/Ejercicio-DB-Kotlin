package com.example.ejerciciodbkotlin.Fragments

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ejerciciodbkotlin.R
import com.example.ejerciciodbkotlin.UI.AdminSQLiteOpenHelper
import com.example.ejerciciodbkotlin.databinding.FragmentEliminarBinding

/**
 * A simple [Fragment] subclass.
 * Use the [EliminarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EliminarFragment : Fragment() {
    private lateinit var binding: FragmentEliminarBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEliminarBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAceptarBorra.setOnClickListener {
            var dni =binding.etDniBorra.text.toString()
            if(!dni.isNullOrEmpty()){
                var admin = AdminSQLiteOpenHelper(context,"escuela",null,1)
                var db = admin.writableDatabase

                var cantidad = db.delete("alumnos","dni='" + dni +"'",null)
                db.close()
                if(cantidad == 1){
                    Toast.makeText(context,"Alumno borrado correctamente",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(context,"Error de borrado",Toast.LENGTH_LONG).show()
                }
                binding.etDniBorra.text.clear()
            }
        }
    }
}