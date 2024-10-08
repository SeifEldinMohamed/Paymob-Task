package com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.api

import com.example.paymobtaskmoviesapp.data.Constants
import com.example.paymobtaskmoviesapp.data.Constants.Companion.MOVIE_ID_KEY
import com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.datamodel.movie_details.MovieDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailsApi {
    @GET("movie/{$MOVIE_ID_KEY}")
    suspend fun fetchMovieDetails(
        @Path(MOVIE_ID_KEY) movieId: Int,
        @Query(Constants.LANGUAGE_KEY) language: String = "en"
    ): Response<MovieDetailsResponse>
}