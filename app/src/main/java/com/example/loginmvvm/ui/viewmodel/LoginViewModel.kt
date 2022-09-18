package com.example.loginmvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginmvvm.data.models.User
import com.example.loginmvvm.domain.completeUser.CompleteUserDataUseCaseImp
import com.example.loginmvvm.domain.createUser.CreateUserUseCaseImp
import com.example.loginmvvm.domain.getUserData.GetUserDataUseCaseImp
import com.example.loginmvvm.domain.googlelogin.SignInGoogleUseCaseImp
import com.example.loginmvvm.domain.logOut.LogoutUseCaseImp
import com.example.loginmvvm.domain.login.LoginUseCaseImp
import com.example.loginmvvm.domain.updateUser.UpdateUserDataUseCaseImp
import com.example.loginmvvm.domain.userExist.UserExistUseCaseImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCaseImp,
    private val createUserUseCase: CreateUserUseCaseImp,
    private val signInGoogleUseCase: SignInGoogleUseCaseImp,
    private val completeUserDataUseCase: CompleteUserDataUseCaseImp,
    private val userExistUseCase: UserExistUseCaseImp,
    private val logoutUseCase: LogoutUseCaseImp,
    private val updateUserDataUseCase: UpdateUserDataUseCaseImp,
    private val getUserDataUseCase: GetUserDataUseCaseImp
    ) : ViewModel(){

    // live data
    val loginLiveData: MutableLiveData<String?> = MutableLiveData()
    var createUserLiveData: MutableLiveData<String?>  = MutableLiveData()
    val signInGoogleLiveData: MutableLiveData<String?> = MutableLiveData()
    val completeUserDataLiveData:MutableLiveData<String?> = MutableLiveData()
    val userExistLiveData:MutableLiveData<String?> = MutableLiveData()
    val updateUserDataLiveData:MutableLiveData<String?> = MutableLiveData()
    val getUserDataLiveData:MutableLiveData<User> = MutableLiveData()



    /*
    // use cases
    //private val createUserUseCase:CreateUserUseCase by lazy { CreateUserUseCaseImp(LoginRepoImp()) }
    //private val signInGoogleUseCase: SignInGoogleUseCase by lazy { SignInGoogleUseCaseImp(LoginRepoImp()) }
    //private val completeUserDataUseCase: CompleteUserDataUseCase by lazy { CompleteUserDataUseCaseImp(LoginRepoImp()) }
    //private val userExistUseCase: UserExistUseCase by lazy { UserExistUseCaseImp(LoginRepoImp()) }
    //private val logoutUseCase: LogoutUseCase by lazy { LogoutUseCaseImp(LoginRepoImp()) }
    //private val updateUserDataUseCase: UpdateUserDataUseCase by lazy { UpdateUserDataUseCaseImp(LoginRepoImp()) }
    private val getUserDataUseCase: GetUserDataUseCase by lazy { GetUserDataUseCaseImp(LoginRepoImp()) }*/


    fun login(email:String, password:String){
        viewModelScope.launch(Dispatchers.IO) {
            val error = loginUseCase(email, password)

            withContext(Dispatchers.Main){
                loginLiveData.value = error
            }
        }
    }


    fun createUser(email: String, password: String,name:String,lastName:String,phone:String){
        viewModelScope.launch(Dispatchers.IO) {
            val error = createUserUseCase(email,password,name,lastName,phone)

            withContext(Dispatchers.Main){
                createUserLiveData.value = error
            }
        }
    }


    fun firebaseAuthWithGoogle(idToken: String){
        viewModelScope.launch(Dispatchers.IO) {
            val error = signInGoogleUseCase(idToken)

            withContext(Dispatchers.Main){
                signInGoogleLiveData.value = error
            }
        }
    }


    fun completeUserData(name:String,lastName: String,phone: String){
        viewModelScope.launch(Dispatchers.IO) {
            val error = completeUserDataUseCase(name, lastName, phone)

            withContext(Dispatchers.Main){
                completeUserDataLiveData.value = error
            }
        }
    }

    fun userExist(){
        viewModelScope.launch(Dispatchers.IO) {
            val error = userExistUseCase()

            withContext(Dispatchers.Main){
                userExistLiveData.value = error
            }
        }
    }


    fun userLogout(){
        viewModelScope.launch(Dispatchers.IO) {
            logoutUseCase()
        }
    }


    fun updateUserData(name:String,lastName: String,phone: String){
        viewModelScope.launch(Dispatchers.IO) {
            val error = updateUserDataUseCase(name, lastName, phone)

            withContext(Dispatchers.Main){
                updateUserDataLiveData.value = error
            }
        }
    }




    fun getUserData(){
        viewModelScope.launch(Dispatchers.IO) {
            val user = getUserDataUseCase()

            withContext(Dispatchers.Main){
                getUserDataLiveData.value = user
            }
        }
    }


}