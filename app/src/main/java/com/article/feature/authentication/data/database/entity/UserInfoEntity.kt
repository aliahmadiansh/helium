package com.article.feature.authentication.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.article.feature.authentication.data.network.model.UserInfoResponse


@Entity(tableName = "user_info")
data class UserInfoEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,
    @ColumnInfo(name = "email_address")
    val emailAddress: String
)

fun UserInfoResponse.toUserInfoEntity(): UserInfoEntity =
    UserInfoEntity(
        id = id,
        firstName = firstName,
        lastName = lastName,
        phoneNumber = phoneNumber,
        emailAddress = email
    )