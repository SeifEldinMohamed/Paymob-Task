package com.example.paymobtaskmoviesapp.presentation.screens.movie_details_screen

import com.example.paymobtaskmoviesapp.presentation.model.CustomApiExceptionUiModel
import com.example.paymobtaskmoviesapp.presentation.model.CustomDatabaseExceptionUiModel
import com.example.paymobtaskmoviesapp.presentation.screens.movie_details_screen.model.MovieDetailsUiModel

data class MovieDetailsUiState(
    val movieDetailsUiModel: MovieDetailsUiModel? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val customApiErrorExceptionUiModel: CustomApiExceptionUiModel? = null,
    val customDatabaseErrorExceptionUiModel: CustomDatabaseExceptionUiModel? = null,
)