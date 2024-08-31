package com.example.paymobtaskmoviesapp.domain.model

data class PopularMovieDomainModel(
    val id: Int,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val overview: String,
    val originalLanguage: String
)
