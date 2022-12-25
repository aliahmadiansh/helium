package com.article.feature.search.data.network.model

import com.google.gson.annotations.SerializedName

data class SearchBody(
    @SerializedName("search_text")
    val searchText: String
)
