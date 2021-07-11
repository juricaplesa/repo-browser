package dev.plesa.data

import dev.plesa.data.remote.model.GitHubRepositoryDTO
import dev.plesa.data.remote.model.GitHubUserDTO
import dev.plesa.domain.Repository
import dev.plesa.domain.Result
import dev.plesa.domain.model.GitHubRepository
import dev.plesa.domain.model.GitHubUser
import dev.plesa.domain.model.RepositoriesSortOption
import dev.plesa.domain.onError
import dev.plesa.domain.onSuccess

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override suspend fun getRepositories(
        query: String,
        sort: RepositoriesSortOption
    ): Result<List<GitHubRepository>> {
        lateinit var repositories: List<GitHubRepositoryDTO>
        remoteDataSource.getRepositories(query, sort)
            .onSuccess { repositories = it }
            .onError { return Result.Error(it) }

        return Result.Success(repositories.map { it.mapToDomain() })
    }

    override suspend fun getNextRepositories(): Result<List<GitHubRepository>> {
        lateinit var repositories: List<GitHubRepositoryDTO>
        remoteDataSource.getNextRepositories()
            .onSuccess { repositories = it }
            .onError { return Result.Error(it) }

        return Result.Success(repositories.map { it.mapToDomain() })
    }

    override suspend fun getUser(url: String): Result<GitHubUser> {
        lateinit var user: GitHubUserDTO
        remoteDataSource.getUser(url)
            .onSuccess { user = it }
            .onError { return Result.Error(it) }

        return Result.Success(user.mapToDomain())
    }
}