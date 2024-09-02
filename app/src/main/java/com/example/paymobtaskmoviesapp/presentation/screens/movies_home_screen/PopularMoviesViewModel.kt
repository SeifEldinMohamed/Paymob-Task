package com.example.paymobtaskmoviesapp.presentation.screens.movies_home_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.paymobtaskmoviesapp.domain.model.CustomApiExceptionDomainModel
import com.example.paymobtaskmoviesapp.domain.usecase.FetchPopularMovieListUseCase
import com.example.paymobtaskmoviesapp.presentation.mapper.toCustomApiExceptionUiModel
import com.example.paymobtaskmoviesapp.presentation.mapper.toPopularMovieUIModel
import com.example.paymobtaskmoviesapp.presentation.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    private val _popularMoviesUiState = MutableStateFlow(PopularMoviesUiState(isLoading = true))
    val popularMoviesUiState get() = _popularMoviesUiState.asStateFlow()

    init {
        requestPopularMovies()
    }

     fun requestPopularMovies() {
        _popularMoviesUiState.value = PopularMoviesUiState(isLoading = true)
        viewModelScope.launch(dispatcher.io) {
            delay(2000)
            try {
                fetchPopularMovieListUseCase()
                    .catch {
                        Log.d("erooooooor", "in first catch ${it.message}")
                        _popularMoviesUiState.value = PopularMoviesUiState(
                            isLoading = false,
                            isError = true,
                             customErrorExceptionUiModel = (it as CustomApiExceptionDomainModel).toCustomApiExceptionUiModel()
                        )
                    }
                    .cachedIn(viewModelScope)
                    .collect { pagingData ->
                        val mappedUiModel = pagingData.map { popularMoviesDomainModel ->
                            popularMoviesDomainModel.toPopularMovieUIModel()
                        }
                        _popularMoviesUiState.value = PopularMoviesUiState(
                            isLoading = false,
                            popularMoviesPagingDataFlow = flowOf(mappedUiModel)
                        )
                    }

            } catch (e: Exception) {
                Log.d("erooooooor", "in second catch ${e.message}")

                _popularMoviesUiState.value = PopularMoviesUiState(
                    isLoading = false,
                    isError = true,
                    customErrorExceptionUiModel = (e as CustomApiExceptionDomainModel).toCustomApiExceptionUiModel()
                )
            }
        }
    }
}