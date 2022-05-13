package com.mongodb.passkeeper.android.screens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mongodb.passkeeper.PassKeeperRepo
import com.mongodb.passkeeper.getNewPassword
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _currentPassword = MutableLiveData<String>()
    val currentPassword = _currentPassword
    private val repo: PassKeeperRepo = PassKeeperRepo()

    val savedPasswords = repo.getAllPassword().asLiveData(Dispatchers.IO)

    init {
        getNewPassword()
    }

    fun onRefreshPassword() {
        getNewPassword()
    }

    private fun getNewPassword() {
        _currentPassword.value = getNewPassword(13)
    }


    fun onSavePassword(pass: String, url: String, name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.saveInfo(name, url, pass)
        }
    }

}