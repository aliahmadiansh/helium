package com.article.feature.profile.ui.viewmodel

import androidx.lifecycle.*
import com.article.core.common.ApiResultWrapper
import com.article.feature.profile.data.repository.ProfileRepositoryImpl
import com.article.feature.profile.ui.view.ProfileArticleView
import com.article.feature.profile.ui.view.ProfileView
import com.article.feature.profile.ui.view.toProfileArticleView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repositoryImpl: ProfileRepositoryImpl,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val userId: Int = savedStateHandle.get<Int>("userId") ?: 0

    private val _snackBarMessage = MutableLiveData<List<String>>()
    val snackBarMessage: LiveData<List<String>> = _snackBarMessage

    private val _profile = MutableLiveData<ProfileView>()
    val profile: MutableLiveData<ProfileView> = _profile

    private val _articleList = MutableLiveData<List<ProfileArticleView>>()
    var articleList: LiveData<List<ProfileArticleView>> = _articleList

    val articleListOther = MutableLiveData<List<ProfileArticleView>>()
    val fullName = MutableLiveData<String>()

    init {
        if (userId == 0) {
            getProfile(repositoryImpl.getUserId())
            articleList = repositoryImpl.getArticleList
        } else {
            getProfile(userId)
        }
    }

    private fun getProfile(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repositoryImpl.profile(id)) {
                is ApiResultWrapper.Success -> {
                    fullName.postValue(response.response.firstName + " " + response.response.lastName)
                    if (userId == 0) {
                        repositoryImpl.saveMyArticleToLocal(response.response)
                    } else {
                        articleListOther.postValue(response.response.articles.data.map {
                            it.toProfileArticleView(
                                lastName = response.response.lastName,
                                firstName = response.response.firstName
                            )
                        })
                    }
                }
                is ApiResultWrapper.ErrorData -> {
                    _snackBarMessage.postValue(response.message)
                }
                ApiResultWrapper.Failure -> {}
            }
        }
    }
}