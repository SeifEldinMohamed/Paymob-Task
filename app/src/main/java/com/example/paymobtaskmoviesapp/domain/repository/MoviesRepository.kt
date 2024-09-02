package com.example.paymobtaskmoviesapp.domain.repository

import androidx.paging.PagingData
import com.example.paymobtaskmoviesapp.domain.model.MovieDetailsDomainModel
import com.example.paymobtaskmoviesapp.domain.model.PopularMovieDomainModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun fetchPopularMovies(): Flow<PagingData<PopularMovieDomainModel>>
    suspend fun fetchMovieDetails(movieId: Int): MovieDetailsDomainModel

    suspend fun insertFavouriteMovie(movieDetailsDomainModel: MovieDetailsDomainModel)
    suspend fun deleteFavouriteMovie(movieDetailsDomainModel: MovieDetailsDomainModel)
    fun isFavouriteMovie(movieId: Int): Flow<Boolean>
}