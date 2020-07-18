package com.example.personalblog.ui.Register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.personalblog.logic.Repository

import com.example.personalblog.logic.model.RegisterRequest

class RegisterViewModel:ViewModel() {
    private val registerRequestLiveData= MutableLiveData<RegisterRequest>()

    val registerLiveData=Transformations.switchMap(registerRequestLiveData){
        registerRequest->Repository.register(registerRequest)
    }

    fun register(registerRequest: RegisterRequest){
        registerRequestLiveData.value=registerRequest
    }
}