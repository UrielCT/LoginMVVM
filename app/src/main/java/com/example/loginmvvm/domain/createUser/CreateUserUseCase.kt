package com.example.loginmvvm.domain.createUser

interface CreateUserUseCase {

   //suspend fun createUserUseCase(email:String, password:String,name:String,lastName:String,phone:String) : String?

   suspend operator fun invoke(email:String, password:String,name:String,lastName:String,phone:String) : String?
}