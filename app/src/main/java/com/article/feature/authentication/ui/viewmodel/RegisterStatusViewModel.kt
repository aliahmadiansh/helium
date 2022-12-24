package com.article.feature.authentication.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterStatusViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val phoneNumber: MutableLiveData<String> =
        MutableLiveData(savedStateHandle.get<String>("phoneNumber").orEmpty())
}