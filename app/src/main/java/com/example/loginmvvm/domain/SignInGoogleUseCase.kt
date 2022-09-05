package com.example.loginmvvm.domain

interface SignInGoogleUseCase {

    suspend fun firebaseAuthWithGoogleUseCase(idToken: String) : String?

}