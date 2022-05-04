package com.mongodb.passkeeper.android

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mongodb.passkeeper.getNewPassword
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {


    private val _currentPassword = MutableLiveData<String>()
    val currentPassword = _currentPassword

    init {
        getNewPassword()
    }

    fun onRefreshPassword() {
        getNewPassword()
    }

    private fun getNewPassword() {
        _currentPassword.value = getNewPassword(13)
    }

}