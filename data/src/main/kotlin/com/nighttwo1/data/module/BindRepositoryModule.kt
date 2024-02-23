package com.nighttwo1.data.module

import com.nighttwo1.data.repository.MovieRepositoryImpl
import com.nighttwo1.domain.repository.MovieRepository
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
}