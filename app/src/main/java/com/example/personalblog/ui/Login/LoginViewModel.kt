package com.example.personalblog.ui.Login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.personalblog.logic.Repository
import com.example.personalblog.logic.model.LoginRequest

class LoginViewModel:ViewModel() {
    private val loginRequestLiveData=MutableLiveData<LoginRequest>()

    val loginLiveData=Transformations.switchMap(loginRequestLiveData){
        loginRequest->Repository.login(loginRequest)
    }

    fun login(loginRequest: LoginRequest){
        loginRequestLiveData.value=loginRequest
    }
}