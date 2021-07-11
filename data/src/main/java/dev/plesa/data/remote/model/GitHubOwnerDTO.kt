package dev.plesa.data.remote.model

import com.squareup.moshi.Json
import dev.plesa.data.common.DomainMapper
import dev.plesa.domain.model.GitHubUser

open class GitHubOwnerDTO : DomainMapper<GitHubUser> {

    @Json(name = "login")
    var name: String = ""
    @Json(name = "avatar_url")
    var avatarUrl: String = ""
    @Json(name = "url")
    var userUrl: String = ""
    @Json(name = "html_url")
    var htmlUrl: String = ""

    override fun mapToDomain() = GitHubUser(
        name,
        avatarUrl,
        userUrl,
        htmlUrl,
        "",
        "",
        "",
        ""
    )

}