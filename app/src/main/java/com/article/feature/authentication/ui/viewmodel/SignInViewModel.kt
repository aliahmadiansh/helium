package com.article.feature.authentication.ui.viewmodel

import com.article.core.common.ApiResultWrapper
import com.article.feature.authentication.data.repository.AuthenticationRepository
import androidx.lifecycle.*
import com.article.feature.authentication.data.database.entity.toUserInfoEntity
import com.article.feature.authentication.data.network.model.UserSignInBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val phoneNumber: MutableLiveData<String> =
        MutableLiveData(savedStateHandle.get<String>("phoneNumber").orEmpty())

    val password = MutableLiveData<String>()

    private val _successSignUp = MutableLiveData<Boolean>()
    val successSignUp: LiveData<Boolean> = _successSignUp

    private val _errorMessage = MutableLiveData<List<String>>()
    val errorMessage: LiveData<List<String>> = _errorMessage

    fun postUserSignIn() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response =
                authenticationRepository.postUserSignIn(
                    UserSignInBody(
                        phoneNumber = phoneNumber.value ?: "",
                        password = password.value ?: ""
                    )
                )){
                is ApiResultWrapper.Success -> {
                    authenticationRepository.saveToken(response.response.accessToken)
                    saveUserInfo()
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

    private fun saveUserInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = authenticationRepository.getUserInfo()){
                is ApiResultWrapper.Success -> {
                    authenticationRepository.addUserInfoToDatabase(
                        response.response.toUserInfoEntity()
                    )
                    authenticationRepository.saveUserID(response.response.id)
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
        if (phoneNumber.value.isNullOrEmpty()) {
            return true
        }
        if (password.value.isNullOrEmpty()) {
            return true
        }
        return false
    }
}