package com.example.loginmvvm.domain

import com.example.loginmvvm.data.network.LoginRepo

class UpdateUserDataUseCaseImp(private val repo: LoginRepo) : UpdateUserDataUseCase {


    override suspend fun updateUserDataUseCase(name: String, lastName: String, phone: String): String? {
        return repo.updateUserDataRepo(name,lastName,phone)
    }

}