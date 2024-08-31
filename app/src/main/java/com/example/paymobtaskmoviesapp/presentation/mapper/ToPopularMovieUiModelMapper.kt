package com.example.paymobtaskmoviesapp.presentation.mapper

import com.example.paymobtaskmoviesapp.domain.model.PopularMovieDomainModel
import com.example.paymobtaskmoviesapp.presentation.screens.movies_home_screen.model.PopularMovieUiModel

fun PopularMovieDomainModel.toPopularMovieUIModel(): PopularMovieUiModel {
    return PopularMovieUiModel(
        id = this.id,
        title = this.title,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        voteAverage = this.voteAverage,
        overview = this.overview,
        originalLanguage = this.originalLanguage
    )
}