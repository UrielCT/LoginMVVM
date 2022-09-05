package com.example.loginmvvm.domain

import com.example.loginmvvm.data.models.User

interface GetUserDataUseCase {

    suspend fun getUserDataUseCase() : User
}