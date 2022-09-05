package com.example.loginmvvm.domain

import com.example.loginmvvm.data.network.LoginRepo

class UserExistUseCaseImp(private val repo: LoginRepo) : UserExistUseCase{

    override suspend fun userExistUseCase(): String? {
        return repo.userExistRepo()
    }
}