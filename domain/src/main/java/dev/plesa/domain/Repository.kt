package dev.plesa.domain

import dev.plesa.domain.model.GitHubUser
import dev.plesa.domain.model.GitHubRepository
import dev.plesa.domain.model.RepositoriesSortOption

interface Repository {

    /**
     * Returns queried list of repositories
     *
     * @param query search query
     * @param sort selected option for sort
     *
     * Can return errors:
     * IncompleteDataException when received list might not be complete
     * MaximumRequestsException when maximum number of requests is reached
     */
    suspend fun getRepositories(query: String, sort: RepositoriesSortOption): Result<List<GitHubRepository>>

    /**
     * Returns next page of queried repositories
     *
     * Can return errors:
     * NextRepositoriesNotFoundException when there is no next page
     * IncompleteDataException when received list might not be complete
     * MaximumRequestsException when maximum number of requests is reached
     */
    suspend fun getNextRepositories(): Result<List<GitHubRepository>>

    /**
     * Returns user
     *
     * @param url details request url for user
     */
    suspend fun getUser(url: String): Result<GitHubUser>

}