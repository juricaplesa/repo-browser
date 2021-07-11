package dev.plesa.domain

import dev.plesa.domain.model.GitHubUser
import dev.plesa.domain.model.GitHubRepository
import dev.plesa.domain.model.RepositoriesSortOption

interface Repository {

    suspend fun getRepositories(query: String, sort: RepositoriesSortOption): Result<List<GitHubRepository>>

    suspend fun getNextRepositories(): Result<List<GitHubRepository>>

    suspend fun getUser(url: String): Result<GitHubUser>

}