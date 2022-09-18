package com.example.loginmvvm.data.network

import com.example.loginmvvm.data.models.User

interface LoginRepo  {


    suspend fun loginRepo(email:String, password:String) : String?


    suspend fun createUserRepo(email: String,password: String,name:String,lastName:String,phone:String) : String?


    suspend fun firebaseAuthWithGoogleRepo(idToken: String) : String?


    suspend fun completeUserDataRepo(name:String,lastName: String,phone: String) : String?


    suspend fun userExistRepo() : String?


    suspend fun logoutRepo()


    suspend fun updateUserDataRepo(name:String,lastName: String,phone: String) :String?


    suspend fun getUserDataRepo() : User

}