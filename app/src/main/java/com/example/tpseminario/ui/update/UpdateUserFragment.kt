package com.example.tpseminario.ui.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.tpseminario.R
import com.example.tpseminario.databinding.FragmentUpdateUserBinding
import com.example.tpseminario.model.User
import com.example.tpseminario.viewmodel.UserViewModel

class UpdateUserFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentUpdateUserBinding
    private var user: User? = null

    private val userViewModel by viewModels<UserViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateUserBinding.inflate(inflater, container, false)

        user = arguments?.getSerializable("user") as User

        binding.etAge.setText(user!!.age.toString())
        binding.etName.setText(user!!.firstName)
        binding.etLastName.setText(user!!.lastName)


        binding.btnUpdate.setOnClickListener {
            validateFields(user!!)
        }


        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)



        return binding.root
    }


    private fun deleteUser(user: User) {
        val dialog = AlertDialog.Builder(requireContext())

        dialog.setNegativeButton("No") { _, _ ->
            //return@setNegativeButton
        }

        dialog.setPositiveButton("Yes") { _,_ ->
            userViewModel.deleteUser(user = user)
            Toast.makeText(requireContext(), "User eliminado!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateUserFragment_to_listFragment)
        }

        dialog.setTitle("¿Desea eliminar?")
        dialog.setMessage("Esta seguro que desea eliminar ha ${user.firstName}")

        dialog.create().show()
    }


    private fun validateFields(user: User) {
        val name = binding.etName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val age = binding.etAge.text.toString()

        if (name.isNotEmpty() && lastName.isNotEmpty() && age.isNotEmpty()) {
            //val ageInt = Integer.parseInt(age)
            //val user = User(id = user.id, firstName = name, lastName = lastName, age =  age.toInt())
            val user2 = user.copy(firstName = name, lastName = lastName, age =  age.toInt())

            userViewModel.updateUser(user = user2)
            findNavController().navigate(R.id.action_updateUserFragment_to_listFragment)
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId) {
            R.id.menu_delete -> {
                deleteUser(user = user!!)
                true
            }

            else -> {
                false
            }
        }
    }


}