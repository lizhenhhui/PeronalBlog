package com.example.personalblog.logic.network

import com.example.personalblog.logic.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @POST("user/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginMsg>

    @POST("user/registerUser")
    fun register(@Body registerRequest: RegisterRequest):Call<RegisterMsg>
}