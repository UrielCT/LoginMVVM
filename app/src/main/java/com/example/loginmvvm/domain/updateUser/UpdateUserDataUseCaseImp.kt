package com.example.loginmvvm.domain.updateUser

import com.example.loginmvvm.data.network.LoginRepoImp
import javax.inject.Inject

class UpdateUserDataUseCaseImp @Inject constructor(private val repo: LoginRepoImp) :
    UpdateUserDataUseCase {


    /*override suspend fun updateUserDataUseCase(name: String, lastName: String, phone: String): String? {
        return repo.updateUserDataRepo(name,lastName,phone)
    }*/

    override suspend fun invoke(name: String, lastName: String, phone: String): String? {
        return repo.updateUserDataRepo(name,lastName,phone)
    }

}