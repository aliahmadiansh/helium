package com.article.feature.authentication.ui.view

import com.article.feature.authentication.ui.viewmodel.SplashViewModel
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.article.R
import com.article.core.common.safeNavigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val viewModel: SplashViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        if (viewModel.isTokenExist){
            lifecycleScope.launch {
                delay(500)
                safeNavigate(R.id.action_splashFragment_to_app_graph)
            }
        }else {
            lifecycleScope.launch {
                delay(500)
                safeNavigate(R.id.action_splashFragment_to_auth_graph)
            }
        }
    }
}