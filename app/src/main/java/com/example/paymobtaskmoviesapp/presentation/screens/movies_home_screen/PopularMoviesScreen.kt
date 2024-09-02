package com.example.paymobtaskmoviesapp.presentation.screens.movies_home_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.paymobtaskmoviesapp.R
import com.example.paymobtaskmoviesapp.domain.model.CustomApiExceptionDomainModel
import com.example.paymobtaskmoviesapp.presentation.common_components.CenterAlignedAppBar
import com.example.paymobtaskmoviesapp.presentation.common_components.ErrorSection
import com.example.paymobtaskmoviesapp.presentation.mapper.toCustomApiExceptionUiModel
import com.example.paymobtaskmoviesapp.presentation.screens.movies_home_screen.components.PopularMovieItem
import com.example.paymobtaskmoviesapp.presentation.screens.movies_home_screen.components.shimmer_loading.AnimateShimmerMoviesList
import com.example.paymobtaskmoviesapp.presentation.screens.movies_home_screen.preview_data.fakePopularMoviesUiState


@ExperimentalMaterial3Api
@Composable
fun PopularMoviesScreen(
    onMovieClick: (id: Int) -> Unit,
    onFavouriteClick: (id: Int) -> Unit,
) {
    val popularMoviesViewModel: PopularMoviesViewModel = hiltViewModel()
    val popularMoviesUiState =
        popularMoviesViewModel.popularMoviesUiState.collectAsState()

    PopularMoviesContent(
        popularMoviesUiState = popularMoviesUiState.value,
        onMovieClick = onMovieClick,
        onFavouriteClick = onFavouriteClick,
        onRefreshButtonClicked = { popularMoviesViewModel.requestPopularMovies() }
    )
}

@ExperimentalMaterial3Api
@Composable
fun PopularMoviesContent(
    popularMoviesUiState: PopularMoviesUiState,
    onMovieClick: (id: Int) -> Unit,
    onFavouriteClick: (id: Int) -> Unit,
    onRefreshButtonClicked: () -> Unit
) {
    val popularMoviesLazyPagingItems =
        popularMoviesUiState.popularMoviesPagingDataFlow.collectAsLazyPagingItems()
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        CenterAlignedAppBar(
            title = R.string.popular_movies_screen_title,
            showBackButton = false
        )

        when {
            popularMoviesUiState.isLoading -> {
                Spacer(modifier = Modifier.height(8.dp))
                AnimateShimmerMoviesList()
            }

            popularMoviesUiState.isError -> {
                ErrorSection(
                    onRefreshButtonClicked = onRefreshButtonClicked,
                    customApiErrorExceptionUiModel = popularMoviesUiState.customErrorExceptionUiModel
                )
            }

            else -> {
                LazyColumn(
                    Modifier.padding(vertical = 8.dp)
                ) {
                    items(popularMoviesLazyPagingItems.itemCount) {
                        val movieUiModelItem = popularMoviesLazyPagingItems[it]
                        movieUiModelItem?.let {
                            PopularMovieItem(
                                popularMovieUiModel = movieUiModelItem,
                                isFavorite = false,
                                onMovieClick = onMovieClick,
                                onFavoriteClick = onFavouriteClick
                            )
                        }
                    }
                    popularMoviesLazyPagingItems.apply {
                        when (loadState.append) {
                            is LoadState.Loading -> {
                                item {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        CircularProgressIndicator(
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                }
                            }

                            is LoadState.Error -> {
                                item {
                                    Text(
                                        text = stringResource(R.string.failed_to_load_more_movies),
                                        color = Color.Red,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }

                            is LoadState.NotLoading -> {}
                        }
                        when(loadState.refresh){
                            is LoadState.Loading -> {}
                            is LoadState.Error -> {
                                item {
                                    ErrorSection(
                                        onRefreshButtonClicked = onRefreshButtonClicked,
                                        customApiErrorExceptionUiModel = ((loadState.refresh as LoadState.Error).error as CustomApiExceptionDomainModel).toCustomApiExceptionUiModel()
                                    )
                                }
                            }

                            is LoadState.NotLoading -> {}
                        }
                    }
                }
            }
        }
    }

}

@ExperimentalMaterial3Api
@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFFEBEBEB)
@Composable
private fun PreviewPopularMoviesContent() {
    PopularMoviesContent(
        popularMoviesUiState = fakePopularMoviesUiState,
        onMovieClick = {},
        onFavouriteClick = {},
        onRefreshButtonClicked = {}
    )
}
