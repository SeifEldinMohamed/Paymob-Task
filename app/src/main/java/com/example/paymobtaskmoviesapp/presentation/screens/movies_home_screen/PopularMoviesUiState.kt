package com.example.paymobtaskmoviesapp.presentation.screens.movies_home_screen

import androidx.paging.PagingData
import com.example.paymobtaskmoviesapp.presentation.model.CustomExceptionUiModel
import com.example.paymobtaskmoviesapp.presentation.screens.movies_home_screen.model.PopularMovieUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class PopularMoviesUiState(
    val popularMoviesPagingDataFlow: Flow<PagingData<PopularMovieUiModel>> = emptyFlow(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val customErrorExceptionUiModel: CustomExceptionUiModel = CustomExceptionUiModel.Unknown
)