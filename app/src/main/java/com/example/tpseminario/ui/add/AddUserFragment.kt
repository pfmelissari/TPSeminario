package com.example.tpseminario.ui.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tpseminario.databinding.FragmentAddUserBinding


class addUserFragment : Fragment() {

    private lateinit var binding: FragmentAddUserBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddUserBinding.inflate(inflater, container, false)


        binding.btnAdd.setOnClickListener{

            val id = binding.etId.text.toString()
            if(id.isBlank()){

                Toast.makeText(requireContext(), "Complete el id", Toast.LENGTH_SHORT).show()
            }

        }


        return binding.root
    }


}