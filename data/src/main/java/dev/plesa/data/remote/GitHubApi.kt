package dev.plesa.data.remote

import dev.plesa.data.remote.model.GitHubItemsDTO
import dev.plesa.data.remote.model.GitHubRepositoryDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {

    @GET("search/repositories")
    suspend fun getRepositories(@Query("q") query: String): GitHubItemsDTO<GitHubRepositoryDTO>

}