package com.example.loginmvvm.domain

interface LoginUseCase {

    suspend fun loginUseCase(email:String, password:String) : String?

}