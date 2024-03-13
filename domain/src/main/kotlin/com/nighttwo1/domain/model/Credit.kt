package com.nighttwo1.domain.model

data class MovieCredits(
    val id: MovieId,
    val cast: List<Cast>
)

@JvmInline
value class CreditId(val value: String)

data class Cast(
    val name: String,
    val profilePath: String?,
    val character: String,
    val creditId: CreditId,
)