package com.nighttwo1.domain.repository

import com.nighttwo1.domain.NetworkResult
import com.nighttwo1.domain.model.Favorite

interface AccountRepository {
    suspend fun setFavorite(favorite: Favorite): NetworkResult<Boolean>
}