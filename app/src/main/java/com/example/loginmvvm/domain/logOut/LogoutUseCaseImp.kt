package com.example.loginmvvm.domain.logOut

import com.example.loginmvvm.data.network.LoginRepoImp
import javax.inject.Inject

class LogoutUseCaseImp @Inject constructor(private val repo: LoginRepoImp) : LogoutUseCase {

    /*override  suspend fun logoutUseCase(){
        return repo.logoutRepo()
    }*/

    override suspend fun invoke() {
        return repo.logoutRepo()
    }


}