package com.example.loginmvvm.ui.view.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.example.loginmvvm.databinding.FragmentLoginBinding
import com.example.loginmvvm.ui.view.activities.UserActivity
import com.example.loginmvvm.ui.viewmodel.LoginViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val REQUEST_CODE_GOOGLE:Int = 1


    private val viewModel: LoginViewModel by viewModels()


    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        val gso: GoogleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this.activity!!.applicationContext, gso)

        val navHostFragment: NavHostFragment = activity!!.supportFragmentManager.findFragmentById(R.id.activity_main_NavHostFragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController


        observeUserAuth()
        observeGoogleAuth()
        observeUserExist(navController)

        binding.btnLogin.setOnClickListener { login() }

        binding.txtGoToRegistrarse.setOnClickListener { goToRegister(navController) }

        binding.btnLoginGoogle.setOnClickListener { signInGoogle() }
    }



    private fun signInGoogle() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, REQUEST_CODE_GOOGLE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_GOOGLE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d("success", "firebaseAuthWithGoogle:" + account.id)

                viewModel.firebaseAuthWithGoogle(account.idToken!!)
                //firebaseAuthWithGoogle(account.idToken!!)

            } catch (e: ApiException) {
                Log.w("error", "Google sign in failed", e)
            }
        }
    }

    // observer del login con google

    private fun observeGoogleAuth(){
        viewModel.signInGoogleLiveData.observe(viewLifecycleOwner, Observer { result ->
            if (result == null){
                Toast.makeText(this.context, "Se inicio sesion con google correctamente" ,Toast.LENGTH_SHORT).show()
                // se loggeo con google
                viewModel.userExist()

            }
            else {
                Toast.makeText(this.context,"No se pudo iniciar sesion co google",Toast.LENGTH_SHORT).show()
            }
        })
    }


    //

    private fun observeUserExist(nav:NavController){
        viewModel.userExistLiveData.observe(viewLifecycleOwner, Observer {
            if (it == "exist"){
                //goToCompleteProfile(nav)
                goToUserActivity(this.context!!.applicationContext)
            }
            else if (it == "none"){

                goToCompleteProfile(nav)
                Toast.makeText(this.context,"El usuario no se pudo almacenar",Toast.LENGTH_SHORT).show()
            }
        })

    }









    private fun login(){
        val email:String = binding.etEmail.text.toString()
        val password:String = binding.etPassword.text.toString()
        if(email.isNotEmpty() && password.isNotEmpty()){
            viewModel.login(email, password)
        }
        else{
            Toast.makeText(this.context,"complete todos lo campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeUserAuth(){
        viewModel.loginLiveData.observe(viewLifecycleOwner, Observer { result->
            if (result == null){
                Toast.makeText(this.context, "Se inicio sesion correctamente" ,Toast.LENGTH_SHORT).show()
                goToUserActivity(this.context!!.applicationContext)
            }
            else {
                Toast.makeText(this.context,"El usuario no existe",Toast.LENGTH_SHORT).show()
            }
        })
    }











    private fun goToUserActivity(context: Context) {
        val intent: Intent = Intent(context, UserActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }


    private fun goToRegister(nav:NavController){
        nav.navigate(R.id.action_loginFragment_to_registerFragment)
        nav.enableOnBackPressed(true)
    }

    private fun goToCompleteProfile(nav:NavController){
        nav.navigate(R.id.action_loginFragment_to_completeProfileFragment)
        nav.enableOnBackPressed(false)
    }

}