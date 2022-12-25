package com.article.feature.profile.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.article.core.ui.model.ItemArticleView
import com.article.core.ui.model.toArticleView
import com.article.feature.profile.data.datasource.local.BookmarkLocalDataSource

import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(private val bookmarkLocalDataSource: BookmarkLocalDataSource) {

    val getBookmarkList: LiveData<List<ItemArticleView>> =
        MediatorLiveData<List<ItemArticleView>>().apply {
            addSource(bookmarkLocalDataSource.getBookmarks()) { list ->
                postValue(list.map { it.toArticleView() })
            }
        }


}