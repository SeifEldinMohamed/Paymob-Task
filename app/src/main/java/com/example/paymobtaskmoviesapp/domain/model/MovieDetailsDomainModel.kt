package com.example.paymobtaskmoviesapp.domain.model

import com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.datamodel.movie_details.Genre

data class MovieDetailsDomainModel(
    val id: Int,
    val name: String,
    val details: String,
    val posterPath: String,
    val categories: List<Genre>,
    val rating: Double,
    val releaseDate: String,
    val isFavourite:Boolean
)
