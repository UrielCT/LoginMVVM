package com.example.loginmvvm.domain.login


interface LoginUseCase {

    suspend operator fun invoke(email:String, password:String) : String?
    //suspend fun loginUseCase(email:String, password:String) : String?
}