package com.example.tpseminario.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tpseminario.R
import com.example.tpseminario.databinding.FragmentListBinding
import com.example.tpseminario.model.UserExample


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding


    private  val myList = arrayListOf(UserExample(id = 1, title = "Dibu", description = "Martinez"))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)

        val adapter = ListAdapter(myList)
        binding.listRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.listRecyclerView.adapter = adapter

        binding.btnAddUser.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addUserFragment)
        }

        return binding.root
    }
}