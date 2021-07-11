package dev.plesa.repobrowser.model

import dev.plesa.domain.model.GitHubUser
import java.io.Serializable

data class GitHubUserUI(
    val name: String,
    val avatarUrl: String,
    val detailsRequestUrl: String,
    val htmlUrl: String,
    val company: String,
    val location: String,
    val email: String,
    val bio: String
) : Serializable

fun GitHubUser.mapToUI() = GitHubUserUI(
    name,
    avatarUrl,
    detailsRequestUrl,
    htmlUrl,
    company,
    location,
    email,
    bio
)