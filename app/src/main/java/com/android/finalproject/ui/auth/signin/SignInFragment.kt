package com.android.finalproject.ui.auth.signin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.android.finalproject.R
import com.android.finalproject.databinding.FragmentSignInBinding
import com.android.finalproject.ui.base.BaseViewModelFragment
import com.android.finalproject.ui.home.HomeActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class SignInFragment : BaseViewModelFragment<SignInViewModel, FragmentSignInBinding>(
    SignInViewModel::class
) {
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initViews() {
        super.initViews()

        binding.etEmail.doOnTextChanged { _, _, _, _ ->
            binding.etEmail.error = null
        }

        binding.etPassword.doOnTextChanged { _, _, _, _ ->
            binding.etPassword.error = null
        }

        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
        }

        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            viewModel.signIn(email, password)
        }

    }

    override fun initObservers() {
        super.initObservers()

        viewModel.signInFormResponseState.onEach {
            when (it) {
                is SignInFormResponseState.EmailIsEmpty -> {
                    binding.etEmail.error = getString(R.string.can_not_be_empty)
                }
                is SignInFormResponseState.PasswordIsEmpty -> {
                    binding.etPassword.error = getString(R.string.can_not_be_empty)
                }
                is SignInFormResponseState.Proceed -> {
                    viewModel.performSignIn(it.email, it.password)
                }
            }
        }.launchIn(lifecycleScope)


        viewModel.signInResponseState.onEach {
            when (it) {
                SignInResponseState.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.invalid_credentials),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is SignInResponseState.Loading -> {
                    if(it.loading)
                        showDialog(getString(R.string.loading))
                    else
                        dismissDialog()
                }
                SignInResponseState.Success -> {
                    val intent = Intent(requireContext(), HomeActivity::class.java)
                    startActivity(intent)
                }
            }
        }.launchIn(lifecycleScope)
    }

}