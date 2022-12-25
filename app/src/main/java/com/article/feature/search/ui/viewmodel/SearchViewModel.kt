package com.article.feature.search.ui.viewmodel

import androidx.lifecycle.*
import com.article.core.common.ApiResultWrapper
import com.article.feature.search.data.network.model.SearchResponse
import com.article.feature.search.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    val searchText = MutableLiveData<String>()
    var searchState = MutableLiveData<SearchState>()
    val progressShow = MutableLiveData<Boolean>()

    private val _errorMessage = MutableLiveData<List<String>>()
    val errorMessage: LiveData<List<String>> = _errorMessage

    val searchResponse = Transformations.switchMap(searchText) {
        liveData(Dispatchers.IO) {
            emit(postSearch(it)).also {
                progressShow.postValue(false)
            }
        }
    }

    private suspend fun postSearch(text: String?): SearchResponse? {
        return if (!text.isNullOrEmpty()) {
            progressShow.postValue(true)
            return when (val response = searchRepository.postSearch(text)) {
                is ApiResultWrapper.Success -> {
                    response.response
                }
                is ApiResultWrapper.ErrorData -> {
                    _errorMessage.postValue(response.message)
                    null
                }
                ApiResultWrapper.Failure -> null
            }
        } else {
            null
        }
    }

    fun changeSearchState(state: SearchState) {
        searchState.value = state
    }

    enum class SearchState {
        POST, TAG, USER
    }
}