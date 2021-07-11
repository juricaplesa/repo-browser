package dev.plesa.data

import dev.plesa.data.remote.model.GitHubRepositoryDTO
import dev.plesa.domain.Result

interface RemoteDataSource {

    suspend fun getRepositories(query: String): Result<List<GitHubRepositoryDTO>>

}