package com.article.feature.article.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.article.core.common.ApiResultWrapper
import com.article.core.ui.model.ItemTagView
import com.article.feature.article.data.newtwork.model.PublishArticleBody
import com.article.feature.article.data.repository.ArticleRepositoryImpl
import com.article.feature.article.data.repository.TagRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PublishArticleViewModel @Inject constructor(
    private val articleRepositoryImpl: ArticleRepositoryImpl,
    private val tagRepositoryImpl: TagRepositoryImpl
) : ViewModel() {

    val articleTitle = MutableLiveData<String>()
    val articleContent = MutableLiveData<String>()
    val tagModel = MutableLiveData<ItemTagView>()

    private val _successPublish = MutableLiveData<Boolean>()
    val successPublish: LiveData<Boolean> = _successPublish

    private val _errorMessage = MutableLiveData<List<String>>()
    val errorMessage: LiveData<List<String>> = _errorMessage

    private val _tags = MutableLiveData<List<ItemTagView>>()
    var tags: LiveData<List<ItemTagView>> = _tags

    init {
        tags = tagRepositoryImpl.getTagListLocal
        getTags()
    }

    private fun getTags() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = tagRepositoryImpl.getTags()) {
                is ApiResultWrapper.Success -> {
                    tagRepositoryImpl.saveTagToLocal(response.response.data)
                }
                is ApiResultWrapper.ErrorData -> {
                    response.message
                }
                ApiResultWrapper.Failure -> {}
            }
        }
    }

    fun publishArticle() {
        viewModelScope.launch(Dispatchers.IO) {
            val response =
                articleRepositoryImpl.publishArticle(
                    PublishArticleBody(
                        title = articleTitle.value ?: "",
                        content = articleContent.value ?: "",
                        tags = tagModel.value?.id.toString()
                    )
                )
            when (response) {
                is ApiResultWrapper.Success -> {
                    _successPublish.postValue(true)
                }
                is ApiResultWrapper.ErrorData -> {
                    _errorMessage.postValue(response.message)
                }
                ApiResultWrapper.Failure -> {
                    _successPublish.postValue(false)
                }
            }
        }
    }

    fun isEmptyValue(): Boolean {
        if (articleTitle.value.isNullOrEmpty()) {
            return true
        }
        if (articleContent.value.isNullOrEmpty()) {
            return true
        }
        if (tagModel.value?.id.toString().isEmpty()) {
            return true
        }
        return false
    }

}