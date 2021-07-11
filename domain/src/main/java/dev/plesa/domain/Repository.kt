package dev.plesa.domain

import dev.plesa.domain.model.GitHubRepository

interface Repository {

    suspend fun getGitHubRepositories(query: String): Result<List<GitHubRepository>>

    fun getGitHubUser()

}