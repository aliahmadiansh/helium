package com.article.feature.profile.data.datasource.local

import androidx.lifecycle.LiveData
import com.article.feature.article.data.database.dao.BookmarkDao
import com.article.feature.article.data.database.entity.BookmarkEntity
import javax.inject.Inject

class BookmarkLocalDataSourceImpl @Inject constructor(private val bookmarkDao: BookmarkDao) :
    BookmarkLocalDataSource {

    override  fun getBookmarks(): LiveData<List<BookmarkEntity>> =
        bookmarkDao.getBookmarks()
}