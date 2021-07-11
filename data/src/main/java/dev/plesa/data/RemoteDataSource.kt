package dev.plesa.data

import dev.plesa.data.remote.model.GitHubRepositoryDTO
import dev.plesa.data.remote.model.GitHubUserDTO
import dev.plesa.domain.Result
import dev.plesa.domain.model.RepositoriesSortOption

interface RemoteDataSource {

    suspend fun getRepositories(
        query: String,
        sort: RepositoriesSortOption
    ): Result<List<GitHubRepositoryDTO>>

    suspend fun getNextRepositories(): Result<List<GitHubRepositoryDTO>>

    suspend fun getUser(url: String): Result<GitHubUserDTO>

}