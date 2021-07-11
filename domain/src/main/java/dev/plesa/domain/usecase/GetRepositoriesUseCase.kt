package dev.plesa.domain.usecase

import dev.plesa.domain.Repository
import dev.plesa.domain.Result
import dev.plesa.domain.model.GitHubRepository
import dev.plesa.domain.model.RepositoriesSortOption

class GetRepositoriesUseCase(
    private val repository: Repository
) {

    suspend fun getRepositories(
        query: String,
        sort: RepositoriesSortOption
    ): Result<List<GitHubRepository>> {
        return if (query.isNotBlank()) {
            repository.getRepositories(query, sort)
        } else {
            Result.Success(emptyList())
        }
    }

    suspend fun getNextRepositories() = repository.getNextRepositories()

}