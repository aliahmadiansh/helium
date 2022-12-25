package com.article.feature.search.data.network.model

import com.article.core.ui.model.ItemArticleView
import com.article.core.ui.model.ItemUserView
import com.google.gson.annotations.SerializedName


data class SearchResponse(
    val users: List<SearchUsersResponse>,
    val tags: List<SearchTagsResponse>,
    val articles: List<SearchArticlesResponse>
) {
    data class SearchUsersResponse(
        val id: Int,
        @SerializedName("first_name")
        val firstName: String,
        @SerializedName("last_name")
        val lastName: String,
        val bio: String?
    ) {
        fun toItemUserView(): ItemUserView =
            ItemUserView(
                id = id,
                firstName = firstName,
                lastName = lastName
            )
    }

    data class SearchTagsResponse(
        val id: Int,
        val name: String
    )

    data class SearchArticlesResponse(
        val id: Int,
        @SerializedName("author_first_name")
        val authorFirstName: String,
        @SerializedName("author_id")
        val authorID: Int,
        @SerializedName("author_last_name")
        val authorLastName: String,
        @SerializedName("author_username")
        val authorUsername: String,
        val content: String,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("read_time_minutes")
        val readTimeMinutes: String,
        val tags: List<SearchTagsResponse>,
        val title: String
    ) {
        fun toItemArticleView(): ItemArticleView =
            ItemArticleView(
                id = id,
                tag = tags[0].name,
                tagId = tags[0].id,
                title = title,
                authorFirstName = authorFirstName,
                authorLastName = authorLastName,
                createdAt = createdAt,
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm34rv5onXNGrPvQIJ3xhcUL5ldjamyQikNQ&usqp=CAU"
            )
    }
}