package com.nighttwo1.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavoriteRequest (
    @SerialName("media_type")
    val mediaType: String,
    @SerialName("media_id")
    val mediaId: Int,
    val favorite: Boolean
)

@Serializable
data class FavoriteResponse(
    val success: Boolean,
    @SerialName("status_code")
    val statusCode: Int,
    @SerialName("status_message")
    val statusMessage: String
)
