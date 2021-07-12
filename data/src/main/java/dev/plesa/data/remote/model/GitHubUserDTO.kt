package dev.plesa.data.remote.model

import com.squareup.moshi.Json
import dev.plesa.data.common.DomainMapper
import dev.plesa.domain.model.GitHubUser

data class GitHubUserDTO(
    @Json(name = "login")
    val name: String,
    @Json(name = "avatar_url")
    val avatarUrl: String,
    @Json(name = "url")
    val userUrl: String,
    @Json(name = "html_url")
    val htmlUrl: String,
    @Json(name = "company")
    val company: String = "",
    @Json(name = "location")
    val location: String = "",
    @Json(name = "email")
    val email: String = "",
    @Json(name = "bio")
    val bio: String = ""
) : DomainMapper<GitHubUser> {

    override fun mapToDomain() = GitHubUser(
        name,
        avatarUrl,
        userUrl,
        htmlUrl,
        company,
        location,
        email,
        bio
    )
}