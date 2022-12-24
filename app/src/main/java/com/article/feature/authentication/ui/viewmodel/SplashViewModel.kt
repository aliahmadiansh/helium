package com.article.feature.authentication.ui.viewmodel
import androidx.lifecycle.ViewModel
import com.article.feature.authentication.data.repository.AuthenticationRepository


import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    authenticationRepository: AuthenticationRepository
) : ViewModel() {

    val isTokenExist = authenticationRepository.checkExistToken()
}