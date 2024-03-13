package com.nighttwo1.data.model

import com.nighttwo1.domain.model.CreditId
import com.nighttwo1.domain.model.MovieId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCredits(
    val id: Int,
    val cast: List<Cast>
)

@Serializable
data class Cast(
    val name: String,
    @SerialName("profile_path")
    val profilePath: String?,
    val character: String,
    @SerialName("credit_id")
    val creditId: String,
    val order: Int,
)

fun MovieCredits.toDomain() = com.nighttwo1.domain.model.MovieCredits(
    id = MovieId(id),
    cast = cast.map {
        com.nighttwo1.domain.model.Cast(
            name = it.name,
            profilePath = it.profilePath,
            character = it.character,
            creditId = CreditId(it.creditId),
        )
    }
)