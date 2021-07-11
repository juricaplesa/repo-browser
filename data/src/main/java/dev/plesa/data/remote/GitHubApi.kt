package dev.plesa.data.remote

import dev.plesa.data.remote.model.GitHubItemsDTO
import dev.plesa.data.remote.model.GitHubRepositoryDTO
import dev.plesa.data.remote.model.GitHubUserDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface GitHubApi {

    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("per_page") pageSize: Int = 20,
        @Query("page") page: Int = 1
    ): Response<GitHubItemsDTO<GitHubRepositoryDTO>>

    @GET
    suspend fun getNextRepositories(@Url url: String): Response<GitHubItemsDTO<GitHubRepositoryDTO>>

    @GET
    suspend fun getUser(@Url url: String): GitHubUserDTO

}