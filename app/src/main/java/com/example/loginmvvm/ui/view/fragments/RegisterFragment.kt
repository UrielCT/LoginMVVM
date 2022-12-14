package com.example.loginmvvm.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.loginmvvm.R
import com.example.loginmvvm.core.isEmailValid
import com.example.loginmvvm.databinding.FragmentRegisterBinding
import com.example.loginmvvm.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment: NavHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.activity_main_NavHostFragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        observeUserRegister()

        binding.btnRegistrarse.setOnClickListener { register() }

        binding.txtGoToLogin.setOnClickListener { goToLogin(navController) }
    }



    private fun register(){
        val name:String = binding.etNombre.text.toString()
        val lastName:String = binding.etApellido.text.toString()
        val email:String = binding.etEmail.text.toString()
        val phone:String = binding.etPhone.text.toString()
        val password:String = binding.etPassword.text.toString()
        val confirmpassword:String = binding.etConfirmPassword.text.toString()

        if (name.isNotEmpty() && lastName.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmpassword.isNotEmpty()){
            if(isEmailValid(email)){
                if (password == confirmpassword){
                    if (password.length >= 6){
                        viewModel.createUser(email, password,name,lastName,phone,)
                    }
                    else{
                        Toast.makeText(this.context,"La contrase??a debe tenes al menos 6 caracteres",Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this.context,"Las contrase??as no coiciden",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this.context,"Ingresaste todos los campos pero el correo no es valido",Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun observeUserRegister(){
        viewModel.createUserLiveData.observe(viewLifecycleOwner, Observer { result->
            if (result == null){
                Toast.makeText(this.context, "el usuario se registr?? correctamente" ,Toast.LENGTH_SHORT).show()

            }
            else {
                Toast.makeText(this.context,"No se pudo registrar el usuario correncamente",Toast.LENGTH_SHORT).show()

            }
        })
    }


    private fun goToLogin(nav:NavController){
        nav.navigate(R.id.action_registerFragment_to_loginFragment)
        nav.enableOnBackPressed(false)
    }



}