package dev.plesa.data

import dev.plesa.data.remote.model.GitHubRepositoryDTO
import dev.plesa.domain.Repository
import dev.plesa.domain.Result
import dev.plesa.domain.model.GitHubRepository
import dev.plesa.domain.onError
import dev.plesa.domain.onSuccess

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override suspend fun getGitHubRepositories(query: String): Result<List<GitHubRepository>> {
        lateinit var repositories: List<GitHubRepositoryDTO>
        remoteDataSource.getRepositories(query)
            .onSuccess { repositories = it }
            .onError { return Result.Error(it) }

        return Result.Success(repositories.map { it.mapToDomain() })
    }

    override fun getGitHubUser() {
        TODO("Not yet implemented")
    }
}