package com.article.feature.article.data.newtwork.api
import com.article.feature.article.data.newtwork.model.ArticleResponse
import com.article.feature.article.data.newtwork.model.DetailArticleResponse
import com.article.feature.article.data.newtwork.model.PublishArticleBody
import com.article.feature.article.data.newtwork.model.PublishArticleResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ArticleApi {

    @GET("articles")
    suspend fun getArticles(): Response<ArticleResponse>

    @POST("articles")
    suspend fun publishArticle(@Body data: PublishArticleBody): Response<PublishArticleResponse>

    @GET("articles")
    suspend fun getArticleInfoByID(@Query("id") id: Int): Response<DetailArticleResponse>

    @GET("articles")
    suspend fun getArticleByTags(@Query("tags") tags: String): Response<ArticleResponse>
}