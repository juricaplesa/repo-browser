package dev.plesa.domain.model

import java.util.*

data class GitHubRepository(
    var name: String,
    var user: GitHubUser,
    var watchersCount: Int,
    var forksCount: Int,
    var issuesCount: Int,
    var programmingLanguage: String,
    var createdAt: Date?,
    var updatedAt: Date?,
    var htmlUrl: String
)
