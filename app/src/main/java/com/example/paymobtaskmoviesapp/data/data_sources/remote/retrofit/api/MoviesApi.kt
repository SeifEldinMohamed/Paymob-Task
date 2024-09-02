package com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.api

import com.example.paymobtaskmoviesapp.data.Constants.Companion.LANGUAGE_KEY
import com.example.paymobtaskmoviesapp.data.Constants.Companion.PAGE_KEY
import com.example.paymobtaskmoviesapp.data.Constants.Companion.POPULAR_MOVIES_ENDPOINT
import com.example.paymobtaskmoviesapp.data.Constants.Companion.STARTING_PAGE_INDEX
import com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.datamodel.movies_list.PopularMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET(POPULAR_MOVIES_ENDPOINT)
    suspend fun fetchPopularMovies(
        @Query(PAGE_KEY) page: Int = STARTING_PAGE_INDEX,
        @Query(LANGUAGE_KEY) language: String = "en"
    ): Response<PopularMoviesResponse>
}