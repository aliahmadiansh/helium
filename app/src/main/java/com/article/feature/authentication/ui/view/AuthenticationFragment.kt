package com.article.feature.authentication.ui.view
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.article.R
import com.article.core.common.safeNavigate
import com.article.databinding.FragmentAuthenticationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationFragment : Fragment() {

    private lateinit var binding: FragmentAuthenticationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthenticationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        binding.btnSigninRegistrationFrag.setOnClickListener {
            safeNavigate(R.id.action_registrationFragment_to_signInFragment)
        }

        binding.btnSignupRegistrationFrag.setOnClickListener {
            safeNavigate(R.id.action_registrationFragment_to_signUpFragment)
        }
    }
}