package com.example.loginmvvm.domain

import com.example.loginmvvm.data.network.LoginRepo

class LogoutUseCaseImp(private val repo: LoginRepo) : LogoutUseCase {

    override suspend fun logoutUseCase(){
        return repo.logoutRepo()
    }


}