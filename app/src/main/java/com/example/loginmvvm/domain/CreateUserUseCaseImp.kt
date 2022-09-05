package com.example.loginmvvm.domain

import com.example.loginmvvm.data.network.LoginRepo

class CreateUserUseCaseImp(private val repo: LoginRepo) : CreateUserUseCase {


    override suspend fun createUserUseCase(email: String, password: String,name:String,lastName:String,phone:String): String? {
        return repo.createUserRepo(email,password,name,lastName,phone)
    }
}