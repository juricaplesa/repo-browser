package dev.plesa.data.remote.model

import com.squareup.moshi.Json

open class GitHubOwnerDTO {
    @Json(name = "login")
    var name: String = ""
    @Json(name = "avatar_url")
    var avatarUrl: String = ""
    @Json(name = "url")
    var userUrl: String = ""
    @Json(name = "html_url")
    var htmlUrl: String = ""
}