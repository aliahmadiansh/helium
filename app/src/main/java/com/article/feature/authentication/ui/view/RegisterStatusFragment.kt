package com.article.feature.authentication.ui.view

import com.article.feature.authentication.ui.viewmodel.RegisterStatusViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.article.core.common.safeNavigate
import com.article.databinding.FragmentRegisterStatusBinding


class RegisterStatusFragment : Fragment() {

    private val viewModel: RegisterStatusViewModel by viewModels()
    private lateinit var binding: FragmentRegisterStatusBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterStatusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.btnSigninRegisterStatusFrag.setOnClickListener {
            safeNavigate(RegisterStatusFragmentDirections.actionRegisterStatusFragmentToSignInFragment(viewModel.phoneNumber.value?:""))
        }
    }
}