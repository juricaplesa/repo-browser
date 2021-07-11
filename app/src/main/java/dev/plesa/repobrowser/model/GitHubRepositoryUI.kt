package dev.plesa.repobrowser.model

import dev.plesa.domain.model.GitHubRepository

data class GitHubRepositoryUI(
    val name: String,
    val ownerAvatarUrl: String
)

fun GitHubRepository.mapToUI(): GitHubRepositoryUI = GitHubRepositoryUI(name, user.avatarUrl)
