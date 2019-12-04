package com.example.a71800662.healthhistoryv1.services

import com.example.a71800662.healthhistoryv1.models.Account
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceAccount {

    @POST("account/auth")
    fun auth(@Body account: Account): Call<Account>

    @POST("account")
    fun signUp(@Body account: Account) : Call<Account>

    @POST("account/forgot")
    fun forgotPassword(@Body account: Account) : Call<Account>

}