package com.example.loginmvvm.domain

import com.example.loginmvvm.data.network.LoginRepo

class CompleteUserDataUseCaseImp(private val repo: LoginRepo) : CompleteUserDataUseCase {

    override suspend fun completeUserDataUseCase(name:String,lastName: String,phone: String): String? {
        return repo.completeUserDataRepo(name,lastName,phone)
    }
}