package com.article.feature.authentication.ui.viewmodel

import com.article.core.common.ApiResultWrapper
import com.article.feature.authentication.data.repository.AuthenticationRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.article.feature.authentication.data.network.model.UserSignUpBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    val firstName = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()
    val userName = MutableLiveData<String>()
    val emailAddress = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _successSignUp = MutableLiveData<Boolean>()
    val successSignUp: LiveData<Boolean> = _successSignUp

    private val _errorMessage = MutableLiveData<List<String>>()
    val errorMessage: LiveData<List<String>> = _errorMessage

    fun postUserSignUp() {
        viewModelScope.launch(Dispatchers.IO) {
            val response =
                authenticationRepository.postUserSignUp(
                    UserSignUpBody(
                        firstName = firstName.value ?: "",
                        lastName = lastName.value ?: "",
                        username = userName.value ?: "",
                        email = emailAddress.value ?: "",
                        phoneNumber = phoneNumber.value ?: "",
                        password = password.value ?: ""
                    )
                )
            when (response){
                is ApiResultWrapper.Success -> {
                    _successSignUp.postValue(true)
                }
                is ApiResultWrapper.ErrorData -> {
                    _errorMessage.postValue(response.message)
                }
                ApiResultWrapper.Failure -> {
                    _successSignUp.postValue(false)
                }
            }
        }
    }

    fun isEmptyValue(): Boolean {
        if (firstName.value.isNullOrEmpty()) {
            return true
        }
        if (lastName.value.isNullOrEmpty()) {
            return true
        }
        if (userName.value.isNullOrEmpty()) {
            return true
        }
        if (emailAddress.value.isNullOrEmpty()) {
            return true
        }
        if (phoneNumber.value.isNullOrEmpty()) {
            return true
        }
        if (password.value.isNullOrEmpty()) {
            return true
        }
        return false
    }
}