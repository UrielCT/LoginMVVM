package com.example.loginmvvm.di

import com.example.loginmvvm.data.network.LoginRepo
import com.example.loginmvvm.data.network.LoginRepoImp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth():FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }


    @Singleton
    @Provides
    fun provideLoginRepository(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): LoginRepo {
        return LoginRepoImp(auth, firestore)
    }



    /*@Singleton
    @Provides
    fun provideLoginUseCase(): Class<LoginUseCase> {
        return LoginUseCase::class.java
    }*/


}