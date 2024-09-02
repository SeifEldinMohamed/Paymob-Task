package com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.datamodel.movies_list

import com.google.gson.annotations.SerializedName

data class PopularMoviesResponse(
    val page: Int,
    @SerializedName("results")
    val movieListDataModel: List<MovieDataModel>,
    val total_pages: Int,
    val total_results: Int
)