package com.example.paymobtaskmoviesapp.di


import com.example.paymobtaskmoviesapp.data.data_sources.remote.MoviesRemoteDataSource
import com.example.paymobtaskmoviesapp.data.repository.MoviesRepositoryImpl
import com.example.paymobtaskmoviesapp.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideTrendingGithubRepositoryImp(
      moviesRemoteDataSource: MoviesRemoteDataSource
    ): MoviesRepository {
        return MoviesRepositoryImpl(moviesRemoteDataSource)
    }

}