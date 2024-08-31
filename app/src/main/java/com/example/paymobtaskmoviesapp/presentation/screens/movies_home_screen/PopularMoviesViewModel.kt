package com.example.paymobtaskmoviesapp.presentation.screens.movies_home_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.map
import com.example.paymobtaskmoviesapp.domain.model.CustomExceptionDomainModel
import com.example.paymobtaskmoviesapp.domain.usecase.FetchPopularMovieListUseCase
import com.example.paymobtaskmoviesapp.presentation.mapper.toCustomExceptionPresentationModel
import com.example.paymobtaskmoviesapp.presentation.mapper.toPopularMovieUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val fetchPopularMovieListUseCase: FetchPopularMovieListUseCase,
    //  private val dispatcher: DispatcherProvider
) : ViewModel() {

    private val _popularMoviesUiState = MutableStateFlow(PopularMoviesUiState(isLoading = false))
    val popularMoviesUiState get() = _popularMoviesUiState.asStateFlow()

    init {
        requestPopularMovies()
    }

    private fun requestPopularMovies() {
        _popularMoviesUiState.value = PopularMoviesUiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                fetchPopularMovieListUseCase()
                    .catch {
                        _popularMoviesUiState.value = PopularMoviesUiState(
                            isLoading = false,
                            isError = true,
                             customErrorExceptionUiModel = (it as CustomExceptionDomainModel).toCustomExceptionPresentationModel()
                        )
                    }
                    .collect { pagingData ->
                        val mappedUiModel = pagingData.map { popularMoviesDomainModel ->
                            Log.d("popular", "data = $popularMoviesDomainModel")
                            popularMoviesDomainModel.toPopularMovieUIModel()
                        }
                        _popularMoviesUiState.value = PopularMoviesUiState(
                            isLoading = false,
                            popularMoviesPagingDataFlow = flowOf(mappedUiModel)
                        )
                    }

            } catch (e: Exception) { // todo remove this catch if not neeeded
                Log.d("popular", e.toString())
                _popularMoviesUiState.value = PopularMoviesUiState(
                    isLoading = false,
                    isError = true,
                    // customErrorExceptionUiModel = (it as CustomExceptionDomainModel).toCustomExceptionPresentationModel()
                )
            }
        }
    }
}