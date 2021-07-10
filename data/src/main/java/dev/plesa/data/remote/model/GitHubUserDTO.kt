package dev.plesa.data.remote.model

import com.squareup.moshi.Json

data class GitHubUserDTO(
    @Json(name = "company")
    val company: String?,
    @Json(name = "location")
    val location: String?,
    @Json(name = "email")
    val email: String?,
    @Json(name = "bio")
    val bio: String?
) : GitHubOwnerDTO()