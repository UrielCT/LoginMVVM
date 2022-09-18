package com.example.loginmvvm.domain.login

import com.example.loginmvvm.data.network.LoginRepoImp
import javax.inject.Inject

class LoginUseCaseImp @Inject constructor(private val repo: LoginRepoImp) : LoginUseCase {

    /*override suspend fun loginUseCase(email: String, password: String): String? {
        return repo.loginRepo(email,password)
    }*/

    override suspend fun invoke(email: String, password: String): String? {
        return repo.loginRepo(email,password)
    }
}