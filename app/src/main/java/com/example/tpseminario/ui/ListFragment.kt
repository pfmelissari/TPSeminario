package com.example.tpseminario.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tpseminario.R
import com.example.tpseminario.databinding.FragmentListBinding
import com.example.tpseminario.model.UserExample
import com.example.tpseminario.viewmodel.UserViewModel


class ListFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentListBinding
    private val userViewModel by viewModels<UserViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)


        val adapter = ListAdapter()
        binding.listRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.listRecyclerView.adapter = adapter


        // Linea divisoria
        val divider = DividerItemDecoration(requireContext(), LinearLayoutManager(requireContext()).orientation)
        binding.listRecyclerView.addItemDecoration(divider)


        binding.btnAddUser.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addUserFragment)
        }

        userViewModel.readAllData.observe(viewLifecycleOwner) { users ->
            adapter.setData(users = users)
        }


        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun deleteAllUser() {
        val dialog = AlertDialog.Builder(requireContext())

        dialog.setNegativeButton("No") { _, _ ->
            //return@setNegativeButton
        }

        dialog.setPositiveButton("Yes") { _,_ ->
            userViewModel.deleteAllUsers()
        }

        dialog.setTitle("¿Desea eliminar?")
        dialog.setMessage("Esta seguro que desea eliminar todos los usuarios")

        dialog.create().show()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId) {
            R.id.menu_delete -> {
                deleteAllUser()
                true
            }

            else -> {
                false
            }
        }
    }

}