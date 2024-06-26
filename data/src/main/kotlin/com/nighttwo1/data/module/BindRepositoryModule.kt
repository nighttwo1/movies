package com.nighttwo1.data.module

import com.nighttwo1.data.repository.AccountRepositoryImpl
import com.nighttwo1.data.repository.MovieRepositoryImpl
import com.nighttwo1.data.repository.SearchRepositoryImpl
import com.nighttwo1.data.repository.TVSeriesRepositoryImpl
import com.nighttwo1.data.repository.TrendingRepositoryImpl
import com.nighttwo1.domain.repository.AccountRepository
import com.nighttwo1.domain.repository.MovieRepository
import com.nighttwo1.domain.repository.SearchRepository
import com.nighttwo1.domain.repository.TVSeriesRepository
import com.nighttwo1.domain.repository.TrendingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface BindRepositoryModule {
    @Binds
    @ViewModelScoped
    fun bindMovieRepository(movieRepository: MovieRepositoryImpl): MovieRepository

    @Binds
    @ViewModelScoped
    fun bindSearchRepository(searchRepository: SearchRepositoryImpl): SearchRepository

    @Binds
    @ViewModelScoped
    fun bindAccountRepository(accountRepository: AccountRepositoryImpl): AccountRepository

    @Binds
    @ViewModelScoped
    fun bindTrendingRepository(trendingRepository: TrendingRepositoryImpl): TrendingRepository

    @Binds
    @ViewModelScoped
    fun bindTVSeriesRepository(tvSeriesRepository: TVSeriesRepositoryImpl): TVSeriesRepository
}