package com.example.loginmvvm.domain

interface CreateUserUseCase {

   suspend fun createUserUseCase(email:String, password:String,name:String,lastName:String,phone:String) : String?

}