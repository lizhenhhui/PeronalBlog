package com.example.personalblog.logic.model

data class LoginRequest(val username:String,val password:String)

data class RegisterRequest(val username: String,val password: String,val email:String)