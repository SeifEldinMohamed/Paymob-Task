package com.example.paymobtaskmoviesapp.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.paymobtaskmoviesapp.presentation.screens.movies_home_screen.PopularMoviesScreen
import com.example.paymobtaskmoviesapp.presentation.utils.Constants.Companion.MOVIE_ID_KEY

@ExperimentalMaterial3Api
@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.MoviesListScreen.route
    ) {
        composable(route = Screens.MoviesListScreen.route) {
            PopularMoviesScreen(
                onMovieClick = { movieId ->
                    navController.navigate(Screens.MovieDetailsScreen.passMovieId(movieId))
                },
                onFavouriteClick = {},
                onRefreshButtonClicked = {}
            )
        }

        composable(
            route = Screens.MovieDetailsScreen.route,
            arguments = listOf(navArgument(MOVIE_ID_KEY) {
                type = NavType.StringType
            })
        ) {
            val movieId = it.arguments?.getString(MOVIE_ID_KEY)
            movieId?.let { id ->

            }
        }
    }
}