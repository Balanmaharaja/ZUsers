package com.balanmaharaja.zusers.module.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.balanmaharaja.zusers.databinding.UserListLayoutBinding
import com.balanmaharaja.zusers.module.adapter.UsersListAdapter
import com.balanmaharaja.zusers.module.model.Data
import com.balanmaharaja.zusers.module.viewmodel.MainViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import network.Result
import java.io.Serializable


class UserListActivity : AppCompatActivity() {


    private lateinit var binding: UserListLayoutBinding

    private val userViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        binding = UserListLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel.getUsers()

        showProgressBar(true)

        userViewModel.userDetailsLD.observe(this) { resource ->
            when (resource) {

                is Result.Success -> {

                    updateUserDetails(resource.value.data)
                }

                is Result.Failure -> {

                    showFailureMessage(message = resource.errorMessage.toString())
                }
            }

            showProgressBar(false)
        }
    }


    private fun updateUserDetails(data : List<Data>)
    {
        binding.usersList.userListValues.apply {

            layoutManager = (LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    baseContext,
                    LinearLayoutManager.VERTICAL)
            )
        }


        binding.usersList.userListValues.adapter = UsersListAdapter(data){

            val intent = Intent(this, UserDetailsActivity::class.java)
            intent.putExtra("data", it)
            startActivity(intent)
        }

        binding.usersList.userListValuesLayout.visibility = View.VISIBLE

        Log.d("result",  data.toString())
    }



    private fun showProgressBar(show : Boolean)
    {
        if(show)
        {
            binding.progressBarLayout.progressBarLayout.visibility = View.VISIBLE
        }
        else
        {
            binding.progressBarLayout.progressBarLayout.visibility = View.GONE
        }
    }


    private fun showFailureMessage(title: String ?= "", message : String)
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setNegativeButton("OK") { _, _ ->
            // Handle negative button click
        }

        val dialog = builder.create()
        dialog.show()
    }
}
