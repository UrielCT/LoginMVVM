package com.example.loginmvvm.ui.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.loginmvvm.data.network.LoginRepoImp
import com.example.loginmvvm.databinding.ActivityUserBinding
import com.example.loginmvvm.domain.LoginUseCaseImp
import com.example.loginmvvm.ui.viewmodel.LoginViewModel
import com.example.loginmvvm.ui.viewmodel.LoginViewModelFactory

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding

    private val viewModel by lazy { ViewModelProvider(this, LoginViewModelFactory(LoginUseCaseImp(
        LoginRepoImp())))[LoginViewModel::class.java] }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getUserData()
        observeUserData()

        binding.btnCerrarSesion.setOnClickListener { cerrarSesion() }

        binding.btnEdit.setOnClickListener { goToEditActivity() }

    }

    override fun onRestart() {
        super.onRestart()
        viewModel.getUserData()
    }


    @SuppressLint("SetTextI18n")
    private fun observeUserData(){
        viewModel.getUserDataLiveData.observe(this, Observer {
            binding.txtName.text = "Nombre: ${it.name}"
            binding.txtLastname.text = "Apellido: ${it.lastname}"
            binding.txtPhone.text = "Teléfono: ${it.phone}"
            binding.txtEmail.text = "Email: ${it.email}"
        })
        Toast.makeText(this,"mostrar Data",Toast.LENGTH_SHORT).show()
    }




    private fun cerrarSesion() {
        viewModel.userLogout()
        val intent: Intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun goToEditActivity() {
        val intent: Intent = Intent(this, EditProfileActivity::class.java)
        startActivity(intent)
    }
}