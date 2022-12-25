package com.article.feature.article.ui.viewmodel

import androidx.lifecycle.*
import com.article.core.common.ApiResultWrapper
import com.article.core.ui.model.ItemArticleView
import com.article.core.ui.model.ItemTagView
import com.article.core.ui.model.toArticleView
import com.article.feature.article.data.database.entity.toArticleEntity
import com.article.feature.article.data.repository.ArticleRepositoryImpl
import com.article.feature.article.data.repository.TagRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val articleRepository: ArticleRepositoryImpl,
    private val tagRepositoryImpl: TagRepositoryImpl
) : ViewModel() {

    val progressShow = MutableLiveData<Boolean>()
    val tagsViewFilter = MutableLiveData<MutableList<ItemTagView>>()

    private val articlesLocal = articleRepository.getArticles()
    private val articleFilter = Transformations.switchMap(tagsViewFilter)
    { item ->
        fetchFilterArticle()
        articleRepository.getArticlesByTag(item.map { it.id })
    }

    val articleList =
        MediatorLiveData<List<ItemArticleView>?>().apply {
            addSource(articlesLocal) {
                if (tagsViewFilter.value.isNullOrEmpty()) {
                    postValue(it.map { item -> item.toArticleView() })
                }
            }
            addSource(articleFilter) {
                postValue(it.map { item -> item.toArticleView() })
            }
        }
    val tagsList = tagRepositoryImpl.getTagListLocal

    init {
        fetchTags()
    }

    fun fetchArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            progressShow.postValue(true)
            when (val response = articleRepository.fetchArticles()) {
                is ApiResultWrapper.Success -> {
                    articleRepository.saveArticleToLocal(response.response.data)
                }
                is ApiResultWrapper.ErrorData -> {
                    // Show SnackBar Message
                    response.message
                }
                ApiResultWrapper.Failure -> {}
            }
        }
    }

    private fun fetchTags() {
        viewModelScope.launch(Dispatchers.IO) {
            progressShow.postValue(true)
            when (val response = tagRepositoryImpl.getTags()) {
                is ApiResultWrapper.Success -> {
                    tagRepositoryImpl.saveTagToLocal(response.response.data)
                }
                is ApiResultWrapper.ErrorData -> {
                    // Show SnackBar Message
                    response.message
                }
                ApiResultWrapper.Failure -> {}
            }
        }
    }

    private fun fetchFilterArticle() {
        viewModelScope.launch(Dispatchers.IO) {
            progressShow.postValue(true)
            val ids = tagsViewFilter.value?.map { it.id }
            when (val response = articleRepository.fetchArticleByTag(
                ids.toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replace(" ", "")
            )) {
                is ApiResultWrapper.Success -> {
                    articleRepository.insertArticles(response.response.data.map { it.toArticleEntity() })
                }
                is ApiResultWrapper.ErrorData -> {}
                ApiResultWrapper.Failure -> {}
            }
        }
    }
}