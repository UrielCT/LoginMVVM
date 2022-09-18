package com.example.loginmvvm.domain.googlelogin

import com.example.loginmvvm.data.network.LoginRepoImp
import javax.inject.Inject

class SignInGoogleUseCaseImp @Inject constructor(private val repo: LoginRepoImp) :
    SignInGoogleUseCase {

    /*override suspend fun firebaseAuthWithGoogleUseCase(idToken: String): String? {
        return repo.firebaseAuthWithGoogleRepo(idToken)
    }*/

    override suspend fun invoke(idToken: String): String? {
        return repo.firebaseAuthWithGoogleRepo(idToken)
    }

}