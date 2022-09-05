package com.example.loginmvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginmvvm.data.models.User
import com.example.loginmvvm.data.network.LoginRepoImp
import com.example.loginmvvm.domain.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel(){

    // live data
    val loginLiveData: MutableLiveData<String> = MutableLiveData()
    var createUserLiveData: MutableLiveData<String>  = MutableLiveData()
    val signInGoogleLiveData: MutableLiveData<String> = MutableLiveData()
    val completeUserDataLiveData:MutableLiveData<String> = MutableLiveData()
    val userExistLiveData:MutableLiveData<String> = MutableLiveData()
    val updateUserDataLiveData:MutableLiveData<String> = MutableLiveData()
    val getUserDataLiveData:MutableLiveData<User> = MutableLiveData()

    // use cases
    private val createUserUseCase:CreateUserUseCase by lazy { CreateUserUseCaseImp(LoginRepoImp()) }
    private val signInGoogleUseCase: SignInGoogleUseCase by lazy { SignInGoogleUseCaseImp(LoginRepoImp()) }
    private val completeUserDataUseCase: CompleteUserDataUseCase by lazy { CompleteUserDataUseCaseImp(LoginRepoImp()) }
    private val userExistUseCase: UserExistUseCase by lazy { UserExistUseCaseImp(LoginRepoImp()) }
    private val logoutUseCase: LogoutUseCase by lazy { LogoutUseCaseImp(LoginRepoImp()) }
    private val updateUserDataUseCase: UpdateUserDataUseCase by lazy { UpdateUserDataUseCaseImp(LoginRepoImp()) }
    private val getUserDataUseCase: GetUserDataUseCase by lazy { GetUserDataUseCaseImp(LoginRepoImp()) }


    fun login(email:String, password:String){
        viewModelScope.launch(Dispatchers.IO) {
            val error = loginUseCase.loginUseCase(email, password)

            withContext(Dispatchers.Main){
                loginLiveData.value = error
            }
        }
    }


    fun createUser(email: String, password: String,name:String,lastName:String,phone:String){
        viewModelScope.launch(Dispatchers.IO) {
            val error = createUserUseCase.createUserUseCase(email,password,name,lastName,phone)

            withContext(Dispatchers.Main){
                createUserLiveData.value = error
            }
        }
    }


    fun firebaseAuthWithGoogle(idToken: String){
        viewModelScope.launch(Dispatchers.IO) {
            val error = signInGoogleUseCase.firebaseAuthWithGoogleUseCase(idToken)

            withContext(Dispatchers.Main){
                signInGoogleLiveData.value = error
            }
        }
    }


    fun completeUserData(name:String,lastName: String,phone: String){
        viewModelScope.launch(Dispatchers.IO) {
            val error = completeUserDataUseCase.completeUserDataUseCase(name, lastName, phone)

            withContext(Dispatchers.Main){
                completeUserDataLiveData.value = error
            }
        }
    }

    fun userExist(){
        viewModelScope.launch(Dispatchers.IO) {
            val error = userExistUseCase.userExistUseCase()

            withContext(Dispatchers.Main){
                userExistLiveData.value = error
            }
        }
    }


    fun userLogout(){
        viewModelScope.launch(Dispatchers.IO) {
            logoutUseCase.logoutUseCase()
        }
    }


    fun updateUserData(name:String,lastName: String,phone: String){
        viewModelScope.launch(Dispatchers.IO) {
            val error = updateUserDataUseCase.updateUserDataUseCase(name, lastName, phone)

            withContext(Dispatchers.Main){
                updateUserDataLiveData.value = error
            }
        }
    }




    fun getUserData(){
        viewModelScope.launch(Dispatchers.IO) {
            val user = getUserDataUseCase.getUserDataUseCase()

            withContext(Dispatchers.Main){
                getUserDataLiveData.value = user
            }
        }
    }


}