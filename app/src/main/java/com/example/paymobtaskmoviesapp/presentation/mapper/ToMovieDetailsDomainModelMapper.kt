package com.example.paymobtaskmoviesapp.presentation.mapper

import com.example.paymobtaskmoviesapp.domain.model.MovieDetailsDomainModel
import com.example.paymobtaskmoviesapp.presentation.screens.movie_details_screen.model.MovieDetailsUiModel

fun MovieDetailsUiModel.toMovieDetailsDomainModel(): MovieDetailsDomainModel {
    return MovieDetailsDomainModel(
        id = this.id,
        name = this.name,
        posterPath = this.image,
        releaseDate = this.releaseDate,
        rating = this.rating,
        details = this.details,
        categories = this.categories,
        isFavourite = this.isFavourite
    )
}