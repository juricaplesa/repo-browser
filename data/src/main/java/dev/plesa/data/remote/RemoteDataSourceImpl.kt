package dev.plesa.data.remote

import dev.plesa.data.RemoteDataSource
import dev.plesa.data.remote.model.GitHubRepositoryDTO
import dev.plesa.domain.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDataSourceImpl(
    private val gitHubApi: GitHubApi
) : RemoteDataSource {


    override suspend fun getRepositories(query: String): Result<List<GitHubRepositoryDTO>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Result.Success(
                    gitHubApi.getRepositories(query).items
                )
            } catch (e: Exception) {
                Result.Error(e)
            }
        }


}