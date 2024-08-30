package com.example.paymobtaskmoviesapp.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.example.paymobtaskmoviesapp.data.data_sources.remote.MoviesRemoteDataSource
import com.example.paymobtaskmoviesapp.data.mapper.toMovieDomainModel
import com.example.paymobtaskmoviesapp.domain.model.PopularMovieDomainModel
import com.example.paymobtaskmoviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) : MoviesRepository {
    override fun fetchPopularMovies(): Flow<PagingData<PopularMovieDomainModel>> {
        return moviesRemoteDataSource.fetchPopularMovies().map { pagingData ->
            pagingData.map { movieDataModel ->
                movieDataModel.toMovieDomainModel()
            }
        }
    }
}