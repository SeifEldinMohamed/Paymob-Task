package com.example.paymobtaskmoviesapp.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.example.paymobtaskmoviesapp.data.data_sources.local.MoviesLocalDataSource
import com.example.paymobtaskmoviesapp.data.data_sources.remote.MoviesRemoteDataSource
import com.example.paymobtaskmoviesapp.data.mapper.toMovieDetailsDomainModel
import com.example.paymobtaskmoviesapp.data.mapper.toMovieDetailsEntity
import com.example.paymobtaskmoviesapp.data.mapper.toMovieDomainModel
import com.example.paymobtaskmoviesapp.domain.model.MovieDetailsDomainModel
import com.example.paymobtaskmoviesapp.domain.model.PopularMovieDomainModel
import com.example.paymobtaskmoviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource
) : MoviesRepository {
    override fun fetchPopularMovies(): Flow<PagingData<PopularMovieDomainModel>> = flow {
        try {
            emitAll(   moviesRemoteDataSource.fetchPopularMovies().map { pagingData ->
                pagingData.map { movieDataModel ->
                    movieDataModel.toMovieDomainModel()
                }
            })

        } catch (e:Exception) {
            throw e
        }
    }


    override suspend fun fetchMovieDetails(movieId: Int): MovieDetailsDomainModel {
        return moviesRemoteDataSource.fetchMovieDetails(movieId = movieId)
            .toMovieDetailsDomainModel()
    }

    override suspend fun insertFavouriteMovie(movieDetailsDomainModel: MovieDetailsDomainModel) {
        moviesLocalDataSource.insertFavouriteMovie(movieDetailsDomainModel.toMovieDetailsEntity())
    }

    override suspend fun deleteFavouriteMovie(movieDetailsDomainModel: MovieDetailsDomainModel) {
        moviesLocalDataSource.deleteFavouriteMovie(movieDetailsDomainModel.toMovieDetailsEntity())
    }

    override fun isFavouriteMovie(movieId: Int): Flow<Boolean> {
        return moviesLocalDataSource.isFavouriteMovie(movieId)
    }
}