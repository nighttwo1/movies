package com.nighttwo1.data.service

import com.nighttwo1.data.model.FavoriteRequest
import com.nighttwo1.data.model.FavoriteResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountService {
    @POST("account/{account_id}/favorite")
    suspend fun setFavorite(
        @Body body: FavoriteRequest
    ): FavoriteResponse
}