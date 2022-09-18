package com.example.loginmvvm.domain.completeUser

import com.example.loginmvvm.data.network.LoginRepoImp
import javax.inject.Inject

class CompleteUserDataUseCaseImp @Inject constructor(private val repo: LoginRepoImp) :
    CompleteUserDataUseCase {

    /*override suspend fun completeUserDataUseCase(name:String,lastName: String,phone: String): String? {
        return repo.completeUserDataRepo(name,lastName,phone)
    }*/

    override suspend fun invoke(name: String, lastName: String, phone: String): String? {
        return repo.completeUserDataRepo(name,lastName,phone)

    }
}