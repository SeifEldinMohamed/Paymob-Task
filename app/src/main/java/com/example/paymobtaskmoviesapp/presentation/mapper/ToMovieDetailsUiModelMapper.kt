package com.example.paymobtaskmoviesapp.presentation.mapper

import com.example.paymobtaskmoviesapp.domain.model.MovieDetailsDomainModel
import com.example.paymobtaskmoviesapp.presentation.screens.movie_details_screen.model.MovieDetailsUiModel

fun MovieDetailsDomainModel.toMovieDetailsUIModel(): MovieDetailsUiModel {
    return MovieDetailsUiModel(
        id = this.id,
        name = this.name,
        image = this.posterPath,
        releaseDate = this.releaseDate,
        rating = this.rating,
        details = this.details,
        categories = this.categories,
        isFavourite = isFavourite
    )
}