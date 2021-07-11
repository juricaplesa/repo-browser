package dev.plesa.domain.usecase

import dev.plesa.domain.Repository

class GetUserUseCase(
    private val repository: Repository
) {

    suspend fun getUser(url: String) = repository.getUser(url)

}