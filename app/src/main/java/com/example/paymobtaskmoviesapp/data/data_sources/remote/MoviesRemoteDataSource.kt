package com.example.paymobtaskmoviesapp.data.data_sources.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.paymobtaskmoviesapp.data.Constants.Companion.PAGING_SIZE
import com.example.paymobtaskmoviesapp.data.data_sources.remote.paging.PopularMoviesPagingSource
import com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.api.MovieDetailsApi
import com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.api.MoviesApi
import com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.datamodel.movie_details.MovieDetailsResponse
import com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.datamodel.movies_list.MovieDataModel
import com.example.paymobtaskmoviesapp.data.mapper.toCustomApiExceptionDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val moviesApi: MoviesApi,
    private val movieDetailsApi: MovieDetailsApi
) {
    fun fetchPopularMovies(): Flow<PagingData<MovieDataModel>> = flow{
        try {
            emitAll(
                Pager(
                    config = PagingConfig(
                        enablePlaceholders = false,
                        pageSize = PAGING_SIZE
                    ),
                    pagingSourceFactory = {
                        PopularMoviesPagingSource(moviesApi)
                    }
                ).flow.catch {
                    throw it.toCustomApiExceptionDomainModel()
                }
            )
        }catch (e:Exception) {
            throw e.toCustomApiExceptionDomainModel()
        }
    }

    suspend fun fetchMovieDetails(movieId: Int): MovieDetailsResponse {
        return try {
            movieDetailsApi.fetchMovieDetails(movieId = movieId).body() as MovieDetailsResponse
        } catch (e:Exception) {
            throw e.toCustomApiExceptionDomainModel()
        }
    }
}