package com.android.finalproject.ui.auth

import android.os.Bundle
import com.android.finalproject.databinding.ActivityAuthBinding
import com.android.finalproject.ui.base.BaseActivity


class AuthActivity : BaseActivity() {
    private var _binding: ActivityAuthBinding? = null
    private val binding: ActivityAuthBinding get() = _binding as ActivityAuthBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}