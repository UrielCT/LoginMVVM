package com.example.loginmvvm.domain.createUser

import com.example.loginmvvm.data.network.LoginRepoImp
import javax.inject.Inject

class CreateUserUseCaseImp @Inject constructor(private val repo: LoginRepoImp) : CreateUserUseCase {

    /*override suspend fun createUserUseCase(email: String, password: String,name:String,lastName:String,phone:String): String? {
        return repo.createUserRepo(email,password,name,lastName,phone)
    }*/

    override suspend fun invoke(
        email: String,
        password: String,
        name: String,
        lastName: String,
        phone: String,
    ): String? {
        return repo.createUserRepo(email,password,name,lastName,phone)
    }
}