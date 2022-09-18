package com.example.loginmvvm.domain.updateUser

interface UpdateUserDataUseCase {

    suspend operator fun invoke(name:String,lastName: String,phone: String) : String?

}