package dev.plesa.repobrowser.model

import dev.plesa.domain.model.GitHubRepository
import dev.plesa.repobrowser.common.domainDateToUI
import java.io.Serializable

data class GitHubRepositoryUI(
    val name: String,
    val user: GitHubUserUI,
    val watchersCount: Int,
    val forksCount: Int,
    val issuesCount: Int,
    val programmingLanguage: String,
    val createdAt: String,
    val updatedAt: String,
    val htmlUrl: String
) : Serializable

fun GitHubRepository.mapToUI() = GitHubRepositoryUI(
    name,
    user.mapToUI(),
    watchersCount,
    forksCount,
    issuesCount,
    programmingLanguage,
    createdAt?.domainDateToUI() ?: "",
    updatedAt?.domainDateToUI() ?: "",
    htmlUrl
)
