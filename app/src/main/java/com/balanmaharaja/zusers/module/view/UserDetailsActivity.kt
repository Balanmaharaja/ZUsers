package com.balanmaharaja.zusers.module.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.balanmaharaja.zusers.R
import com.balanmaharaja.zusers.databinding.UserDetailsLayoutBinding
import com.balanmaharaja.zusers.databinding.UserListLayoutBinding
import com.balanmaharaja.zusers.module.model.Data

class UserDetailsActivity : AppCompatActivity() {

    private lateinit var binding: UserDetailsLayoutBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = UserDetailsLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        {
            intent.getSerializableExtra("data", Data::class.java)
        }
        else
        {
            intent.getSerializableExtra("data") as Data
        }
    }


    override fun onBackPressed() {
        this.finish()
    }
}