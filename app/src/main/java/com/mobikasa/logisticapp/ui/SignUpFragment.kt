package com.mobikasa.logisticapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mobikasa.logisticapp.R
import com.mobikasa.logisticapp.databinding.FragmentSignUpBinding
import com.mobikasa.logisticapp.utils.extensions.viewBinding
import com.mobikasa.logisticapp.viewmodels.MainViewModel

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {
    private val binding by viewBinding(FragmentSignUpBinding::bind)
    private val viewModel: MainViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
        binding.button2.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
        }
        viewModel.title.postValue("Sign Up")
    }
}