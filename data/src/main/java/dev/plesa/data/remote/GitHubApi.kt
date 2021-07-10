package dev.plesa.data.remote

import retrofit2.http.GET

interface GitHubApi {

    @GET("search/repositories")
    suspend fun getRepositories()

}