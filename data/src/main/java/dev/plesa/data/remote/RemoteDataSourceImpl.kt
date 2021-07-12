package dev.plesa.data.remote

import dev.plesa.data.RemoteDataSource
import dev.plesa.data.common.LINK_NEXT
import dev.plesa.data.common.getLinks
import dev.plesa.data.remote.model.GitHubItemsDTO
import dev.plesa.data.remote.model.GitHubRepositoryDTO
import dev.plesa.data.remote.model.GitHubUserDTO
import dev.plesa.data.remote.model.MAXIMUM_REQUESTS
import dev.plesa.domain.Result
import dev.plesa.domain.common.IncompleteDataException
import dev.plesa.domain.common.MaximumRequestsException
import dev.plesa.domain.common.NextRepositoriesNotFoundException
import dev.plesa.domain.model.RepositoriesSortOption
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.util.*

class RemoteDataSourceImpl(
    private val gitHubApi: GitHubApi
) : RemoteDataSource {

    private var nextRepositoriesUrl: String? = null

    override suspend fun getRepositories(
        query: String,
        sort: RepositoriesSortOption
    ): Result<List<GitHubRepositoryDTO>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                nextRepositoriesUrl = null

                val response =
                    gitHubApi.getRepositories(query, sort.name.lowercase(Locale.getDefault()))
                handleRepositoriesResponse(response)

            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun getNextRepositories(): Result<List<GitHubRepositoryDTO>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val url = nextRepositoriesUrl
                    ?: return@withContext Result.Error(NextRepositoriesNotFoundException())

                val response = gitHubApi.getNextRepositories(url)
                handleRepositoriesResponse(response)

            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    private fun handleRepositoriesResponse(response: Response<GitHubItemsDTO<GitHubRepositoryDTO>>): Result<List<GitHubRepositoryDTO>> {
        val result = response.body()

        return if (response.isSuccessful && result != null) {
            if (result.areResultsIncomplete) {
                nextRepositoriesUrl = null
                Result.Error(IncompleteDataException())
            } else {
                nextRepositoriesUrl = response.headers().getLinks()[LINK_NEXT]
                Result.Success(result.items)
            }
        } else {
            if (response.code() == MAXIMUM_REQUESTS) {
                Result.Error(MaximumRequestsException())
            } else {
                Result.Error(HttpException(response))
            }
        }
    }

    override suspend fun getUser(url: String): Result<GitHubUserDTO> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Result.Success(gitHubApi.getUser(url))
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

}