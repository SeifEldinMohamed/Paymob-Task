package com.example.paymobtaskmoviesapp.presentation.screens.movie_details_screen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paymobtaskmoviesapp.domain.model.CustomApiExceptionDomainModel
import com.example.paymobtaskmoviesapp.domain.model.CustomDatabaseExceptionDomainModel
import com.example.paymobtaskmoviesapp.domain.usecase.DeleteFavouriteMovieUseCase
import com.example.paymobtaskmoviesapp.domain.usecase.FetchMovieDetailsUseCase
import com.example.paymobtaskmoviesapp.domain.usecase.InsertFavouriteMovieUseCase
import com.example.paymobtaskmoviesapp.domain.usecase.IsFavouriteMovieUseCase
import com.example.paymobtaskmoviesapp.presentation.mapper.toCustomApiExceptionUiModel
import com.example.paymobtaskmoviesapp.presentation.mapper.toCustomDatabaseExceptionUiModel
import com.example.paymobtaskmoviesapp.presentation.mapper.toMovieDetailsDomainModel
import com.example.paymobtaskmoviesapp.presentation.mapper.toMovieDetailsUIModel
import com.example.paymobtaskmoviesapp.presentation.screens.movie_details_screen.MovieDetailsUiState
import com.example.paymobtaskmoviesapp.presentation.screens.movie_details_screen.model.MovieDetailsUiModel
import com.example.paymobtaskmoviesapp.presentation.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val fetchMovieDetailsUseCase: FetchMovieDetailsUseCase,
    private val insertFavouriteMovieUseCase: InsertFavouriteMovieUseCase,
    private val deleteFavouriteMovieUseCase: DeleteFavouriteMovieUseCase,
    private val isFavouriteMovieUseCase: IsFavouriteMovieUseCase,
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    private val _movieDetailsUiStateFlow = MutableStateFlow(MovieDetailsUiState(isLoading = false))
    val movieDetailsUiStateFlow get() = _movieDetailsUiStateFlow.asStateFlow()

    fun requestMovieDetails(movieId: Int) {
        _movieDetailsUiStateFlow.value = MovieDetailsUiState(isLoading = true)
        viewModelScope.launch(dispatcher.io) {
            try {
                val movieDetailsDomainModel = fetchMovieDetailsUseCase(movieId)
                _movieDetailsUiStateFlow.value = MovieDetailsUiState(
                    isLoading = false,
                    movieDetailsUiModel = movieDetailsDomainModel.toMovieDetailsUIModel()
                )

            } catch (e: Exception) {
                _movieDetailsUiStateFlow.value = MovieDetailsUiState(
                    isLoading = false,
                    isError = true,
                    customApiErrorExceptionUiModel = (e as CustomApiExceptionDomainModel).toCustomApiExceptionUiModel()
                )
            }
        }
    }

    fun insertFavouriteMovieDetails(movieDetailsUiModel: MovieDetailsUiModel) {
        viewModelScope.launch(dispatcher.io) {
            try {
                insertFavouriteMovieUseCase(movieDetailsUiModel.toMovieDetailsDomainModel())
            } catch (e: Exception) {
                _movieDetailsUiStateFlow.value = MovieDetailsUiState(
                    isError = true,
                    customDatabaseErrorExceptionUiModel = (e as CustomDatabaseExceptionDomainModel).toCustomDatabaseExceptionUiModel()
                )
            }
        }
    }

    fun deleteFavouriteMovieDetails(movieDetailsUiModel: MovieDetailsUiModel) {
        viewModelScope.launch(dispatcher.io) {
            try {
                deleteFavouriteMovieUseCase(movieDetailsUiModel.toMovieDetailsDomainModel())
            } catch (e: Exception) {
                _movieDetailsUiStateFlow.value = MovieDetailsUiState(
                    isError = true,
                    customDatabaseErrorExceptionUiModel = (e as CustomDatabaseExceptionDomainModel).toCustomDatabaseExceptionUiModel()
                )
            }
        }
    }

    fun isFavouriteMovieDetails(movieId: Int): Flow<Boolean> {
        return isFavouriteMovieUseCase(movieId)
    }
}