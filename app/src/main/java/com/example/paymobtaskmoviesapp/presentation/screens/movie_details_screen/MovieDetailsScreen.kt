package com.example.paymobtaskmoviesapp.presentation.screens.movie_details_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.paymobtaskmoviesapp.presentation.common_components.ErrorSection
import com.example.paymobtaskmoviesapp.presentation.screens.movie_details_screen.components.Categories
import com.example.paymobtaskmoviesapp.presentation.screens.movie_details_screen.components.MovieDetailsAppBar
import com.example.paymobtaskmoviesapp.presentation.screens.movie_details_screen.components.MovieImageBanner
import com.example.paymobtaskmoviesapp.presentation.screens.movie_details_screen.components.MovieInfo
import com.example.paymobtaskmoviesapp.presentation.screens.movie_details_screen.components.MovieNameAndRating
import com.example.paymobtaskmoviesapp.presentation.screens.movie_details_screen.components.shimmer_loading.AnimateShimmerDetails
import com.example.paymobtaskmoviesapp.presentation.screens.movie_details_screen.model.MovieDetailsUiModel
import com.example.paymobtaskmoviesapp.presentation.screens.movie_details_screen.preview_data.fakeMovieDetailsUiState
import com.example.paymobtaskmoviesapp.presentation.screens.movie_details_screen.viewmodel.MovieDetailsViewModel
import com.example.paymobtaskmoviesapp.presentation.theme.PaymobTaskMoviesAppTheme

@Composable
fun MovieDetailsScreen(
    movieId: Int,
    onBackArrowPressed: () -> Unit
) {
    val movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()
    LaunchedEffect(key1 = Unit) {
        movieDetailsViewModel.requestMovieDetails(movieId)
        movieDetailsViewModel.isFavouriteMovieDetails(movieId)
    }
    val isFavoriteMovie = movieDetailsViewModel.isFavouriteMovieDetails(movieId)
        .collectAsStateWithLifecycle(initialValue = false).value
    val movieDetailsUiState by movieDetailsViewModel.movieDetailsUiStateFlow.collectAsStateWithLifecycle()

    MovieDetailsContent(
        movieDetailsUiState,
        onRefreshClicked = {
            movieDetailsViewModel.requestMovieDetails(movieId)
        },
        onBackArrowPressed = onBackArrowPressed,
        makeFavoriteMovie = isFavoriteMovie,
        onFavouriteButtonClicked = { makeFavourite, movieDetailsUiModel ->
            if (makeFavourite)
                movieDetailsViewModel.insertFavouriteMovieDetails(
                    movieDetailsUiModel.copy(
                        isFavourite = true
                    )
                )
            else
                movieDetailsViewModel.deleteFavouriteMovieDetails(movieDetailsUiModel)

        }
    )
}

@Composable
fun MovieDetailsContent(
    movieDetailsUiState: MovieDetailsUiState,
    onBackArrowPressed: () -> Unit,
    onFavouriteButtonClicked: (isFavourite: Boolean, movieDetailsUiModel: MovieDetailsUiModel) -> Unit,
    onRefreshClicked: () -> Unit,
    makeFavoriteMovie: Boolean
) {

    val movieDetailsUiModelState = remember {
        mutableStateOf<MovieDetailsUiModel?>(null)
    }

    when {
        movieDetailsUiState.isLoading -> {
            AnimateShimmerDetails()
        }

        movieDetailsUiState.isError -> {
            if (movieDetailsUiState.customApiErrorExceptionUiModel != null) {
                ErrorSection(
                    onRefreshButtonClicked = onRefreshClicked,
                    customApiErrorExceptionUiModel = movieDetailsUiState.customApiErrorExceptionUiModel
                )
            } else {
                ErrorSection(
                    onRefreshButtonClicked = onRefreshClicked,
                    customDatabaseExceptionUiModel = movieDetailsUiState.customDatabaseErrorExceptionUiModel
                )
            }

        }

        movieDetailsUiState.movieDetailsUiModel != null -> {
            val movieDetailsUiModel = movieDetailsUiState.movieDetailsUiModel
            movieDetailsUiModelState.value = movieDetailsUiModel
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        MovieImageBanner(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(450.dp),
                            movieImage = movieDetailsUiModel.image,
                        )
                    }

                    item {
                        MovieNameAndRating(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth(),
                            movieName = movieDetailsUiModel.name,
                            rating = movieDetailsUiModel.rating.toFloat(),
                        )
                    }

                    item {
                        MovieInfo(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            movieDetails = movieDetailsUiModel.details,
                            movieReleaseDate = movieDetailsUiModel.releaseDate,
                        )
                    }

                    item {
                        Categories(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            categories = movieDetailsUiModel.categories
                        )
                    }

                }
                MovieDetailsAppBar(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.TopCenter)
                        .fillMaxWidth(),
                    isLiked = makeFavoriteMovie,
                    onFavouriteButtonClicked = {
                        Log.d("favourite", "in onFavouriteButtonClicked $it")
                        onFavouriteButtonClicked(it, movieDetailsUiModel)
                    },
                    onBackArrowPressed = onBackArrowPressed
                )
            }
        }

    }
}

@Preview
@Composable
fun FilmDetailsScreenPreview() {
    PaymobTaskMoviesAppTheme {
        MovieDetailsContent(
            movieDetailsUiState = fakeMovieDetailsUiState,
            onBackArrowPressed = {},
            onFavouriteButtonClicked = { _, _ -> },
            {},
            makeFavoriteMovie = false
        )
    }
}
