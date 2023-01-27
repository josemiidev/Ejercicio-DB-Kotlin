package com.example.ejerciciodbkotlin.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.Navigation
import com.example.ejerciciodbkotlin.R
import com.example.ejerciciodbkotlin.UI.Alumno
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
    }
}
