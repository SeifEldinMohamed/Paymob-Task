package com.example.paymobtaskmoviesapp.presentation.screens.movies_home_screen.model

data class PopularMovieUiModel(
    val id: Int,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val overview: String,
    val originalLanguage: String
)
