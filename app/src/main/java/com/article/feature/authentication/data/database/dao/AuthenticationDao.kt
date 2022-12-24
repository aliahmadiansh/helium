package com.article.feature.authentication.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.article.feature.authentication.data.database.entity.UserInfoEntity


@Dao
interface AuthenticationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUserInfo(user: UserInfoEntity)

    @Query("SELECT * FROM user_info LIMIT 1")
    suspend fun getUserInfo(): UserInfoEntity
}