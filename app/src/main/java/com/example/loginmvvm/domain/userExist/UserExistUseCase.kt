package com.example.loginmvvm.domain.userExist

interface UserExistUseCase {

    suspend operator fun invoke() : String?
}