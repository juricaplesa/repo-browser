package dev.plesa.domain.usecase

import dev.plesa.domain.Repository
import dev.plesa.domain.model.RepositoriesSortOption

class GetRepositoriesUseCase(
    private val repository: Repository
) {

    suspend fun getRepositories(query: String, sort: RepositoriesSortOption) = repository.getRepositories(query, sort)

    suspend fun getNextRepositories() = repository.getNextRepositories()

}