package dev.plesa.data.remote

import dev.plesa.data.RemoteDataSource

class RemoteDataSourceImpl(
    private val gitHubApi: GitHubApi
) : RemoteDataSource {

    override suspend fun getRepositories() = gitHubApi.getRepositories()

}