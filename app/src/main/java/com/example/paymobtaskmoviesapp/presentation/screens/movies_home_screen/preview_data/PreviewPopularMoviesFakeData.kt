package com.example.paymobtaskmoviesapp.presentation.screens.movies_home_screen.preview_data

import androidx.compose.runtime.Composable
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.paymobtaskmoviesapp.presentation.screens.movies_home_screen.PopularMoviesUiState
import com.example.paymobtaskmoviesapp.presentation.screens.movies_home_screen.model.PopularMovieUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


val fakePopularMovieUiModel =
    PopularMovieUiModel(
    id = 1,
    title = "The Shawshank Redemption",
    posterPath = "https://www.vintagemovieposters.co.uk/wp-content/uploads/2023/03/IMG_1887-scaled.jpeg",
    releaseDate = "1994-09-23",
    voteAverage = 8.6,
    overview = "Two imprisoned men forge a remarkable bond over the years, forming a system of mutual support and survival.",
    originalLanguage = "en"
)


val fakePopularMovies = listOf(
    PopularMovieUiModel(
        id = 1,
        title = "The Shawshank Redemption",
        posterPath = "https://image.tmdb.org/t/p/w500/q6y0Go1POy89lB7qAUcKc59FH.jpg",
        releaseDate = "1994-09-23",
        voteAverage = 8.6,
        overview = "Two imprisoned men forge a remarkable bond over the years, forming a system of mutual support and survival.",
        originalLanguage = "en"
    ),
    PopularMovieUiModel(
        id = 2,
        title = "The Godfather",
        posterPath = "https://image.tmdb.org/t/p/w500/1GVnPQcMd5V4s6g553089N5Qp.jpg",
        releaseDate = "1972-03-24",
        voteAverage = 8.5,
        overview = "The Corleone family is one of the most powerful crime families in New York City.",
        originalLanguage = "en"
    ),
)

val fakePopularMoviesUiState = PopularMoviesUiState(
    isLoading = false,
    isError = false,
    popularMoviesPagingDataFlow = flowOf(PagingData.from(fakePopularMovies))
)
