package dev.plesa.data

import dev.plesa.domain.Repository
import dev.plesa.domain.Result
import dev.plesa.domain.model.GitHubRepository

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : Repository {
    override fun getGitHubRepositories(query: String): Result<List<GitHubRepository>> {
        TODO("Not yet implemented")
    }

    override fun getGitHubUser() {
        TODO("Not yet implemented")
    }
}