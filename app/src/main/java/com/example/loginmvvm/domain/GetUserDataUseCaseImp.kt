package com.example.loginmvvm.domain

import com.example.loginmvvm.data.models.User
import com.example.loginmvvm.data.network.LoginRepo

class GetUserDataUseCaseImp(private val repo: LoginRepo) : GetUserDataUseCase {
    override suspend fun getUserDataUseCase(): User {
        return repo.getUserDataRepo()
    }
}