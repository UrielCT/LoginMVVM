package com.example.loginmvvm.domain.userExist

import com.example.loginmvvm.data.network.LoginRepoImp
import javax.inject.Inject

class UserExistUseCaseImp @Inject constructor(private val repo: LoginRepoImp) : UserExistUseCase {

    /*override suspend fun userExistUseCase(): String? {
        return repo.userExistRepo()
    }*/

    override suspend fun invoke(): String? {
        return repo.userExistRepo()
    }
}