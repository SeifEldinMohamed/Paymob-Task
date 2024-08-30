package com.example.paymobtaskmoviesapp.data.data_sources.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.paymobtaskmoviesapp.data.Constants.Companion.PAGING_SIZE
import com.example.paymobtaskmoviesapp.data.data_sources.remote.paging.PopularMoviesPagingSource
import com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.api.MoviesApi
import com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.datamodel.movies_list.MovieDataModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val moviesApi: MoviesApi
) {
    fun fetchPopularMovies(): Flow<PagingData<MovieDataModel>> {
          return  Pager(
            config = PagingConfig(
                enablePlaceholders = false,
                pageSize = PAGING_SIZE
            ),
            pagingSourceFactory = {
                PopularMoviesPagingSource(moviesApi)
            }
        ).flow
    }
}