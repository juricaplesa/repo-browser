package dev.plesa.data.remote.model

import com.squareup.moshi.Json
import dev.plesa.data.common.DomainMapper
import dev.plesa.domain.model.GitHubRepository
import java.util.*

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
    val openIssuesCount: Int,
    @Json(name = "language")
    val programmingLanguage: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "html_url")
    val htmlUrl: String
) : DomainMapper<GitHubRepository> {

    override fun mapToDomain(): GitHubRepository {
        return GitHubRepository(name, owner.mapToDomain(), "", 0, 0, 0, "", Date(), Date(), "", "")
    }

}
