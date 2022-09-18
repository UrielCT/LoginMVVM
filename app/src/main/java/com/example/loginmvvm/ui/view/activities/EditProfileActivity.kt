package com.example.loginmvvm.ui.view.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.loginmvvm.databinding.ActivityEditProfileBinding
import com.example.loginmvvm.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    private val viewModel: LoginViewModel by viewModels()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeUserDataUpdated()

        binding.btnUpdateProfile.setOnClickListener { updateUserProfile() }

    }

    private fun observeUserDataUpdated(){
        viewModel.updateUserDataLiveData.observe(this, Observer {
            if (it == null){
                finish()
            }
            else{
                Toast.makeText(this,"no se pudo editar el prefil",Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateUserProfile(){
        val name: String = binding.etName.text.toString()
        val lastName: String = binding.etLastname.text.toString()
        val phone: String = binding.etPhone.text.toString()

        viewModel.updateUserData(name,lastName,phone)

    }
}