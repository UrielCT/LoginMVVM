package com.example.loginmvvm.domain.getUserData

import com.example.loginmvvm.data.models.User

interface GetUserDataUseCase {

    suspend operator fun invoke() : User
}