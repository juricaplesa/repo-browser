package dev.plesa.data

interface RemoteDataSource {

    suspend fun getRepositories()

}