package com.balanmaharaja.zusers.module.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balanmaharaja.zusers.module.model.ResponseListUsers
import com.balanmaharaja.zusers.module.repo.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import network.Result

class MainViewModel : ViewModel()
{

    private val _userDetails = MutableLiveData<Result<ResponseListUsers>>()
    val userDetailsLD: LiveData<Result<ResponseListUsers>> get() = _userDetails


   fun getUsers() {
       viewModelScope.launch(Dispatchers.IO) {
           _userDetails.postValue(UserRepository().getUsers())
        }
    }

}