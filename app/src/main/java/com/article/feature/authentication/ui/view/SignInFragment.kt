package com.article.feature.authentication.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.article.R
import com.article.core.common.appSnackBar
import com.article.core.common.safeNavigate
import com.article.databinding.FragmentSignInBinding
import com.article.feature.authentication.ui.viewmodel.SignInViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private val viewModel: SignInViewModel by viewModels()
    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserve()
    }

    private fun initView() {
        binding.signinViewModel = viewModel
        binding.lifecycleOwner = this

        binding.tvSignupSigninFragment.setOnClickListener {
            safeNavigate(
                SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
            )
        }

        binding.btnSigninSigninFragment.setOnClickListener {
            if (
                binding.etLayoutPhoneNumberSigninFragment.error == null
                && binding.etLayoutPasswordSigninFragment.error == null
                && !viewModel.isEmptyValue()
            ) {
                binding.btnSigninSigninFragment.visibility = View.INVISIBLE
                binding.progressBtnSigninFragment.visibility = View.VISIBLE
                viewModel.postUserSignIn()
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
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.signin_success),
                    Toast.LENGTH_LONG
                ).show()
                safeNavigate(

                    SignInFragmentDirections.actionSignInFragmentToHomeFragment()
                )
            } else {
                binding.btnSigninSigninFragment.visibility = View.VISIBLE
                binding.progressBtnSigninFragment.visibility = View.GONE
                requireView().appSnackBar(resources.getString(R.string.signup_error))
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { list ->
            binding.btnSigninSigninFragment.visibility = View.VISIBLE
            binding.progressBtnSigninFragment.visibility = View.GONE
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