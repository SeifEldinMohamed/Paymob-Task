package com.example.paymobtaskmoviesapp.data.mapper

import com.example.paymobtaskmoviesapp.data.data_sources.local.room.entities.MovieDetailsEntity
import com.example.paymobtaskmoviesapp.domain.model.MovieDetailsDomainModel

fun MovieDetailsDomainModel.toMovieDetailsEntity(): MovieDetailsEntity {
    return MovieDetailsEntity(
        id = this.id,
        name = this.name,
        image = this.posterPath,
        releaseDate = this.releaseDate,
        rating = this.rating,
        details = this.details,
        categories = this.categories,
        isFavourite = this.isFavourite
    )
}
