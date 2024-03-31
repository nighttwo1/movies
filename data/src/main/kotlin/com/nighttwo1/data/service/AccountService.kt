package com.nighttwo1.data.service

import com.nighttwo1.data.model.FavoriteRequest
import com.nighttwo1.data.model.FavoriteResponse
import com.nighttwo1.data.model.WatchlistRequest
import com.nighttwo1.data.model.WatchlistResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountService {
    @POST("account/{account_id}/favorite")
    suspend fun setFavorite(
        @Body body: FavoriteRequest
    ): FavoriteResponse

    @POST("account/{account_id}/watchlist")
    suspend fun setWatchList(
        @Body body: WatchlistRequest
    ): WatchlistResponse
}