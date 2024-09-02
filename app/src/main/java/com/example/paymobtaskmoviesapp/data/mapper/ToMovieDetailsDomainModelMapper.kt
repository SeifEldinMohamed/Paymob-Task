package com.example.paymobtaskmoviesapp.data.mapper

import com.example.paymobtaskmoviesapp.data.Constants.Companion.IMAGE_BASE_URL
import com.example.paymobtaskmoviesapp.data.data_sources.local.room.entities.MovieDetailsEntity
import com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.datamodel.movie_details.MovieDetailsResponse
import com.example.paymobtaskmoviesapp.domain.model.MovieDetailsDomainModel

fun MovieDetailsResponse.toMovieDetailsDomainModel(): MovieDetailsDomainModel {
    return MovieDetailsDomainModel(
        id = this.id,
        name = this.original_title,
        posterPath = IMAGE_BASE_URL + this.poster_path,
        releaseDate = this.release_date,
        rating = this.vote_average,
        details = this.overview,
        categories = this.genres,
        isFavourite = false
    )
}

fun MovieDetailsEntity.toMovieDetailsDomainModel(): MovieDetailsDomainModel {
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