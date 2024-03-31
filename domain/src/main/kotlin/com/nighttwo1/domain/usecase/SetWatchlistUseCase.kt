package com.nighttwo1.domain.usecase

import com.nighttwo1.domain.model.Watchlist
import com.nighttwo1.domain.repository.AccountRepository
import javax.inject.Inject

class SetWatchlistUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    operator fun invoke(watchlist: Watchlist) = accountRepository.setWatchList(watchlist)
}