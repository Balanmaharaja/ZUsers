package com.balanmaharaja.zusers.module.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.balanmaharaja.zusers.databinding.UsersListItemBinding
import com.balanmaharaja.zusers.module.model.Data


class UsersListAdapter(private val users: List<Data>, private val onItemClick: (Data) -> Unit) : RecyclerView.Adapter<UsersListAdapter.UserViewHolder>() {


    inner class UserViewHolder(private val binding: UsersListItemBinding) : ViewHolder(binding.root) {
        fun bind(user : Data) {
            binding.title.text = user.getFullName()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListAdapter.UserViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = UsersListItemBinding.inflate(layoutInflater, parent, false)
        return UserViewHolder(binding)
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])

        holder.itemView.setOnClickListener {
            onItemClick(users[position])
        }
    }


    override fun getItemCount(): Int {
        return users.size
    }
}