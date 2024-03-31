package com.nighttwo1.domain.repository

import com.nighttwo1.domain.NetworkResult
import com.nighttwo1.domain.model.Favorite
import com.nighttwo1.domain.model.Watchlist
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun setFavorite(favorite: Favorite): NetworkResult<Boolean>
    fun setWatchList(watchlist: Watchlist): Flow<NetworkResult<Boolean>>
}