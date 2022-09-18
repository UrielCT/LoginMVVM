package com.example.loginmvvm.domain.completeUser

interface CompleteUserDataUseCase {

    suspend operator fun invoke(name:String,lastName: String,phone: String) : String?

}