package com.example.loginmvvm.domain

interface UpdateUserDataUseCase {

    suspend fun updateUserDataUseCase(name:String,lastName: String,phone: String) : String?

}