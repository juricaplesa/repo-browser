package dev.plesa.data.remote.model

import com.squareup.moshi.Json

data class GitHubItemsDTO<T>(
    @Json(name = "items")
    val items: List<T>
)
