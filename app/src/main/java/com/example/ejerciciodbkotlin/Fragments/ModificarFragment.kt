package com.example.ejerciciodbkotlin.Fragments

import android.content.ContentValues
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.ejerciciodbkotlin.R
import com.example.ejerciciodbkotlin.UI.AdminSQLiteOpenHelper
import com.example.ejerciciodbkotlin.databinding.FragmentModificarBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ModificarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ModificarFragment : Fragment() {
    private lateinit var binding: FragmentModificarBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModificarBinding.inflate(layoutInflater)
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
        binding.spSexoModifica.adapter = adapter

        var dni = arguments?.getString("dni")?.let { binding.etDniModifica.setText(it) }
        arguments?.getString("nombre")?.let { binding.etNombreModifica.setText(it) }
        arguments?.getString("apellidos")?.let { binding.etApellidosModifica.setText(it) }
        var sexo = arguments?.getString("sexo")

        if (sexo?.uppercase().equals("HOMBRE")) {
            binding.spSexoModifica.setSelection(0)
        } else {
            binding.spSexoModifica.setSelection(1)
        }
        binding.btnCancelarModifica.setOnClickListener {
            if (dni != null) {
                val navController = view?.let { Navigation.findNavController(it) }
                navController?.navigate(R.id.nav_listar)
            }
        }

        binding.btnAceptarModifica.setOnClickListener {
            var dniM = binding.etDniModifica.text.toString()
            var nombreM = binding.etNombreModifica.text.toString()
            var apellidosM = binding.etApellidosModifica.text.toString()
            var sexoM = binding.spSexoModifica.selectedItem.toString()

            if(!dniM.isNullOrEmpty() && !nombreM.isNullOrEmpty() && !apellidosM.isNullOrEmpty()){
                var admin = AdminSQLiteOpenHelper(context,"escuela",null,1)
                var db = admin.writableDatabase

                var datos = ContentValues()

                datos.put("dni",dniM)
                datos.put("nombre",nombreM)
                datos.put("apellidos",apellidosM)
                datos.put("sexo",sexoM)

                var cantidad = db.update("alumnos",datos,"dni='" + dniM + "'",null)

                db.close()

                if(cantidad ==1){
                    Toast.makeText(context,"Alumno Modificado Correctamente",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(context,"Error al modificar",Toast.LENGTH_LONG).show()
                }


            }
        }
    }
}
