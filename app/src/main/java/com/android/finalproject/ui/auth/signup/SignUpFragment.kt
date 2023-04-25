package com.android.finalproject.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.android.finalproject.R
import com.android.finalproject.databinding.FragmentSignUpBinding
import com.android.finalproject.ui.base.BaseViewModelFragment
import com.android.finalproject.ui.home.HomeActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class SignUpFragment : BaseViewModelFragment<SignUpViewModel, FragmentSignUpBinding>(
    SignUpViewModel::class
) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initViews() {
        super.initViews()

        binding.etName.doOnTextChanged { _, _, _, _ ->
            binding.etName.error = null
        }

        binding.etEmail.doOnTextChanged { _, _, _, _ ->
            binding.etEmail.error = null
        }

        binding.etPassword.doOnTextChanged { _, _, _, _ ->
            binding.etPassword.error = null
        }


        binding.btnSignUp.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            viewModel.signUp(name, email, password)
        }
    }


    override fun initObservers() {
        super.initObservers()

        viewModel.signUpFormResponseState.onEach {
            when (it) {
                SignUpFormResponseState.EmailIsEmpty -> {
                    binding.etEmail.error = getString(R.string.can_not_be_empty)
                }
                SignUpFormResponseState.EmailIsNotValid -> {
                    binding.etEmail.error = getString(R.string.invalid_email)
                }
                SignUpFormResponseState.NameIsEmpty -> {
                    binding.etName.error = getString(R.string.can_not_be_empty)
                }
                SignUpFormResponseState.PasswordIsEmpty -> {
                    binding.etPassword.error = getString(R.string.can_not_be_empty)
                }
                is SignUpFormResponseState.Proceed -> {
                    viewModel.performSignUp(it.name, it.email, it.password)
                }
            }
        }.launchIn(lifecycleScope)


        viewModel.signUpResponseState.onEach {
            when(it){
                SignUpResponseState.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        "Email Already Registered.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is SignUpResponseState.Loading -> {
                    if(it.loading)
                        showDialog(getString(R.string.loading))
                    else
                        dismissDialog()
                }
                SignUpResponseState.Success -> {
                    val intent = Intent(requireContext(), HomeActivity::class.java)
                    startActivity(intent)
                }
            }
        }.launchIn(lifecycleScope)
    }
}