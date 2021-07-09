package dev.plesa.domain

import dev.plesa.domain.model.GitHubRepository

interface Repository {

    fun getGitHubRepositories(query: String): Result<List<GitHubRepository>>

    fun getGitHubUser()

}