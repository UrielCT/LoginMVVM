package com.example.loginmvvm.ui.view.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.loginmvvm.databinding.FragmentCompleteProfileBinding
import com.example.loginmvvm.ui.view.activities.UserActivity
import com.example.loginmvvm.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompleteProfileFragment : Fragment() {

    private var _binding: FragmentCompleteProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCompleteProfileBinding.inflate(inflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUserDataCompleted()

        binding.btnConfirm.setOnClickListener { completeUserData() }

    }

    private fun observeUserDataCompleted() {
        viewModel.completeUserDataLiveData.observe(viewLifecycleOwner, Observer { result ->
            if (result == null){
                Toast.makeText(this.context,"los datos se almacenaron correctamente",Toast.LENGTH_SHORT).show()

                goToUserActivity(requireContext())
            }
            else{
                Toast.makeText(this.context,"No se pudieron almacenar los datos",Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun completeUserData(){
        val name = binding.etNombre.text.toString()
        val lastName = binding.etApellido.text.toString()
        val phone = binding.etPhone.text.toString()

        if (name.isNotEmpty() && lastName.isNotEmpty() && phone.isNotEmpty()){
            viewModel.completeUserData(name,lastName,phone)
        }
        else{
            Toast.makeText(this.context,"complete todos los campos",Toast.LENGTH_SHORT).show()
        }
    }







    private fun goToUserActivity(context: Context) {
        val intent: Intent = Intent(context, UserActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

}