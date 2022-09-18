package com.example.loginmvvm.domain.getUserData

import com.example.loginmvvm.data.models.User
import com.example.loginmvvm.data.network.LoginRepoImp
import javax.inject.Inject

class GetUserDataUseCaseImp @Inject constructor(private val repo: LoginRepoImp) :
    GetUserDataUseCase {
    /*override suspend fun getUserDataUseCase(): User {
        return repo.getUserDataRepo()
    }*/

    override suspend fun invoke(): User {
        return repo.getUserDataRepo()
    }
}