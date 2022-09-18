package com.example.loginmvvm.domain.googlelogin

interface SignInGoogleUseCase {

    suspend operator fun invoke(idToken: String) : String?


    //suspend fun firebaseAuthWithGoogleUseCase(idToken: String) : String?

}