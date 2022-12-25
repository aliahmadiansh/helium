package com.article.feature.profile.data.datasource.local

import androidx.lifecycle.LiveData
import com.article.feature.article.data.database.entity.BookmarkEntity

interface BookmarkLocalDataSource {

     fun getBookmarks():LiveData<List<BookmarkEntity>>

}