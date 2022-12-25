package com.article.feature.article.ui.viewmodel

import com.article.feature.article.data.repository.ArticleRepositoryImpl
import androidx.lifecycle.*
import com.article.core.common.ApiResultWrapper
import com.article.core.ui.model.toArticleDetailView
import com.article.feature.article.data.database.entity.toArticleEntity
import com.article.feature.article.data.database.entity.toBookmarkEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailArticleViewModel @Inject constructor(
    private val articleRepository: ArticleRepositoryImpl,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val articleID: Int = savedStateHandle.get<Int>("articleID") ?: 0

    val bookmarkState = MutableLiveData<Boolean>()
    val progressShow = MutableLiveData<Boolean>()
    private val getArticleInfo = articleRepository.getArticleByID(articleID)

    private val _snackBarMessage = MutableLiveData<List<String>>()
    val snackBarMessage: LiveData<List<String>> = _snackBarMessage

    private val _snackBarBookmark = MutableLiveData<Boolean>()
    val snackBarBookmark: LiveData<Boolean> = _snackBarBookmark

    val detailArticle = Transformations.map(getArticleInfo) {
        if (it != null) {
            progressShow.postValue(false)
            it.toArticleDetailView()
        }else {
            insertArticle()
            null
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            bookmarkState.postValue(articleRepository.getBookmarked(articleID))
            insertArticle()
        }
    }

    private fun insertArticle() {
        viewModelScope.launch(Dispatchers.IO) {
            progressShow.postValue(true)
            when (val response = articleRepository.fetchArticleByID(articleID)) {
                is ApiResultWrapper.Success -> {
                    articleRepository.insertArticles(listOf(response.response.data.toArticleEntity()))
                }
                is ApiResultWrapper.ErrorData -> {
                    _snackBarMessage.postValue(response.message)
                }
                ApiResultWrapper.Failure -> {}
            }
        }
    }

    fun setStateBookmark() {
        bookmarkState.value = bookmarkState.value != true
        if (bookmarkState.value == true) {
            viewModelScope.launch(Dispatchers.IO) {
                getArticleInfo.value?.let {
                    articleRepository.insertBookmark(
                        it.toBookmarkEntity()
                    )
                    _snackBarBookmark.postValue(true)
                }
            }
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                getArticleInfo.value?.let {
                    articleRepository.deleteBookmarked(
                        it.toBookmarkEntity()
                    )
                    _snackBarBookmark.postValue(false)
                }
            }
        }
    }
}