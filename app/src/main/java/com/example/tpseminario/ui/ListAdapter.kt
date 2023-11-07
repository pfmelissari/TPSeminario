package com.example.tpseminario.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tpseminario.databinding.ItemListRecyclerviewBinding
import com.example.tpseminario.model.UserExample


class ListAdapter(private val userExampleList: List<UserExample>) : RecyclerView.Adapter<ListAdapter.ListViewHolder>(){

    inner class ListViewHolder(private val binding: ItemListRecyclerviewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(userExample: UserExample) {
            binding.tvIdUser.text = userExample.id.toString()
            binding.tvTitleUser.text = userExample.title
            binding.tvSubTitleUser.text = userExample.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding = binding)
    }

    override fun getItemCount(): Int {
        return userExampleList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        //holder.binding.tvIdUser.text = userList.get(position).id.toString()
        val user = userExampleList.get(position)
        holder.bind(user)
    }

}