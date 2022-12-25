package com.article.feature.profile.ui.viewmodel

import com.article.feature.profile.data.repository.BookmarkRepositoryImpl
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.article.core.ui.model.ItemArticleView
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(bookmarkRepositoryImpl: BookmarkRepositoryImpl) :
    ViewModel() {

    private val _bookmarks = MutableLiveData<List<ItemArticleView>>()
    var bookmarks: LiveData<List<ItemArticleView>> = _bookmarks

    init {
        bookmarks = bookmarkRepositoryImpl.getBookmarkList
    }
}