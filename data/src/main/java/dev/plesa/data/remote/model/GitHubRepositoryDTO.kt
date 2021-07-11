package dev.plesa.data.remote.model

import com.squareup.moshi.Json
import dev.plesa.data.common.DomainMapper
import dev.plesa.data.common.remoteDateToDomain
import dev.plesa.domain.model.GitHubRepository

data class GitHubRepositoryDTO(
    @Json(name = "name")
    val name: String,
    @Json(name = "owner")
    val owner: GitHubOwnerDTO,
    @Json(name = "watchers_count")
    val watchersCount: Int,
    @Json(name = "forks_count")
    val forksCount: Int,
    @Json(name = "open_issues_count")
    val issuesCount: Int,
    @Json(name = "language")
    val programmingLanguage: String?,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "html_url")
    val htmlUrl: String
) : DomainMapper<GitHubRepository> {

    override fun mapToDomain() = GitHubRepository(
        name,
        owner.mapToDomain(),
        watchersCount,
        forksCount,
        issuesCount,
        programmingLanguage ?: "",
        createdAt.remoteDateToDomain(),
        updatedAt.remoteDateToDomain(),
        htmlUrl,
    )

}
