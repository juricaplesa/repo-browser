package dev.plesa.domain.model

data class GitHubUser(
    var name: String,
    var avatarUrl: String,
    var detailsRequestUrl: String,
    var htmlUrl: String,
    var company: String,
    var location: String,
    var email: String,
    var bio: String
)
