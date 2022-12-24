package com.article.feature.authentication.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.article.R
import com.article.core.common.appSnackBar
import com.article.core.common.safeNavigate
import com.article.databinding.FragmentSignUpBinding
import com.article.feature.authentication.ui.viewmodel.SignUpViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private val viewModel: SignUpViewModel by viewModels()
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserve()
    }

    private fun initView() {

        binding.signupViewModel = viewModel
        binding.lifecycleOwner = this

        binding.tvSigninSignupFragment.setOnClickListener {
            safeNavigate(
                SignUpFragmentDirections.actionSignUpFragmentToSignInFragment(
                    viewModel.phoneNumber.value ?: ""
                )
            )
        }

        binding.btnSignupSignupFragment.setOnClickListener {
            if (
                binding.etLayoutNameSignupFragment.error == null
                && binding.etLayoutFamilySignupFragment.error == null
                && binding.etLayoutUsernameSignupFragment.error == null
                && binding.etLayoutEmailSignupFragment.error == null
                && binding.etLayoutPhoneNumberSignupFragment.error == null
                && binding.etLayoutPasswordSignupFragment.error == null
                && !viewModel.isEmptyValue()
            ) {
                binding.btnSignupSignupFragment.visibility = View.INVISIBLE
                binding.progressBtnSignupFragment.visibility = View.VISIBLE
                viewModel.postUserSignUp()
            } else if (viewModel.isEmptyValue()) {
                requireView().appSnackBar(resources.getString(R.string.error_empty_input))
            } else {
                requireView().appSnackBar(resources.getString(R.string.error_validation))
            }
        }
    }

    private fun initObserve() {
        viewModel.successSignUp.observe(viewLifecycleOwner) {
            if (it) {
                safeNavigate(
                    SignUpFragmentDirections.actionSignUpFragmentToRegisterStatusFragment(
                        viewModel.phoneNumber.value ?: ""
                    )
                )
            } else {
                binding.btnSignupSignupFragment.visibility = View.VISIBLE
                binding.progressBtnSignupFragment.visibility = View.GONE
                requireView().appSnackBar(resources.getString(R.string.signup_error))
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { list ->
            binding.btnSignupSignupFragment.visibility = View.VISIBLE
            binding.progressBtnSignupFragment.visibility = View.GONE
            val message = buildString {
                list.forEach {
                    if (this.isEmpty())
                        this.append(it)
                    else
                        this.append("\n$it")
                }
            }
            requireView().appSnackBar(message)
        }
    }
}