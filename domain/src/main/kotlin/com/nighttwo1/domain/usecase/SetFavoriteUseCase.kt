package com.nighttwo1.domain.usecase

import com.nighttwo1.domain.model.Favorite
import com.nighttwo1.domain.repository.AccountRepository
import javax.inject.Inject

class SetFavoriteUseCase @Inject constructor(
    private val accountRepository: AccountRepository
){
    suspend operator fun invoke(favorite: Favorite) = accountRepository.setFavorite(favorite)
}