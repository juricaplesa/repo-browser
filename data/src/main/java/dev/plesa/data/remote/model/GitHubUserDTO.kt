package dev.plesa.data.remote.model

import com.squareup.moshi.Json
import dev.plesa.data.common.DomainMapper
import dev.plesa.domain.model.GitHubUser

data class GitHubUserDTO(
    @Json(name = "company")
    val company: String?,
    @Json(name = "location")
    val location: String?,
    @Json(name = "email")
    val email: String?,
    @Json(name = "bio")
    val bio: String?
) : GitHubOwnerDTO(), DomainMapper<GitHubUser> {

    override fun mapToDomain() = GitHubUser(
        name,
        avatarUrl,
        userUrl,
        htmlUrl,
        company ?: "",
        location ?: "",
        email ?: "",
        bio ?: ""
    )
}