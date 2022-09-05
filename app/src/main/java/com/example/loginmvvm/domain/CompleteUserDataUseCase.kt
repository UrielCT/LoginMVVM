package com.example.loginmvvm.domain

interface CompleteUserDataUseCase {

    suspend fun completeUserDataUseCase(name:String,lastName: String,phone: String) : String?

}