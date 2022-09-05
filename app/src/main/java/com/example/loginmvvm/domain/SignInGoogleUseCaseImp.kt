package com.example.loginmvvm.domain

import com.example.loginmvvm.data.network.LoginRepo

class SignInGoogleUseCaseImp(private val repo: LoginRepo) : SignInGoogleUseCase {

    override suspend fun firebaseAuthWithGoogleUseCase(idToken: String): String? {
        return repo.firebaseAuthWithGoogleRepo(idToken)
    }
}