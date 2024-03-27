package com.nighttwo1.data.repository

import com.nighttwo1.data.model.FavoriteRequest
import com.nighttwo1.data.service.AccountService
import com.nighttwo1.domain.NetworkResult
import com.nighttwo1.domain.model.Favorite
import com.nighttwo1.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountService: AccountService,
) : AccountRepository {
    override suspend fun setFavorite(favorite: Favorite): NetworkResult<Boolean> {
        val response = accountService.setFavorite(
            FavoriteRequest(favorite.type, favorite.id, favorite.favorite)
        )
        return when (response.success) {
            true -> NetworkResult.Success(true)
            false -> NetworkResult.Error(IllegalStateException(response.statusMessage))
        }
    }
}