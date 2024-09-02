package com.example.paymobtaskmoviesapp.presentation.screens.movie_details_screen.model

import com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.datamodel.movie_details.Genre

data class MovieDetailsUiModel(
    val id: Int,
    val name: String,
    val details: String,
    val image: String,
    val categories: List<Genre>,
    val rating: Double,
    val releaseDate: String,
    val isFavourite:Boolean
)
