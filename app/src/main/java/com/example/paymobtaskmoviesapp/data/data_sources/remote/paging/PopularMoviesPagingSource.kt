package com.example.paymobtaskmoviesapp.data.data_sources.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.api.MoviesApi
import com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.datamodel.movies_list.MovieDataModel
import com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.datamodel.movies_list.MovieResponse
import com.example.paymobtaskmoviesapp.data.mapper.toCustomExceptionDomainModel
import kotlinx.coroutines.delay

class PopularMoviesPagingSource(private val moviesApi: MoviesApi) :PagingSource<Int, MovieDataModel>() {
    override fun getRefreshKey(state: PagingState<Int, MovieDataModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDataModel> {
        return try {
            val nextPage = params.key ?: 1
            delay(2000)
            val popularMovies = moviesApi.getPopularMovies(nextPage).body() as MovieResponse
            LoadResult.Page(
                data = popularMovies.movieListDataModel,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (popularMovies.movieListDataModel.isEmpty()) null else popularMovies.page + 1
            )
        }catch (exception: Exception){
            return LoadResult.Error(exception.toCustomExceptionDomainModel())
        }
    }
}