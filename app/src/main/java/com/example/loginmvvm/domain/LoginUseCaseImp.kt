package com.example.loginmvvm.domain

import com.example.loginmvvm.data.network.LoginRepo

class LoginUseCaseImp(private val repo: LoginRepo) : LoginUseCase{

    override suspend fun loginUseCase(email: String, password: String): String? {
        return repo.loginRepo(email,password)
    }
}