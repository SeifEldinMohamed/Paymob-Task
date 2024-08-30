package com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.api

import com.example.paymobtaskmoviesapp.data.Constants.Companion.API_KEY
import com.example.paymobtaskmoviesapp.data.Constants.Companion.STARTING_PAGE_INDEX
import com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.datamodel.movies_list.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
//    @GET("/3/movie/popular?api_key=078e8fe79377bcac312b276a6f7ed8fa")
//    fun getPopular(): Response<MovieDataModel>


    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): Response<MovieResponse>
}