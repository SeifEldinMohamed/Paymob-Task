package com.example.domain.usecase.details

import com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.datamodel.movie_details.Genre
import com.example.paymobtaskmoviesapp.domain.model.MovieDetailsDomainModel

val fakeDetailsDomainModel = MovieDetailsDomainModel(
    id = 12345,
    name = "The Shawshank Redemption",
    details = "A classic prison escape story.",
    posterPath = "https://image.tmdb.org/t/p/w500/q6y0Go1POy89lB7qAUcKc59FH.jpg",
    categories = listOf(Genre(1,"Drama"), Genre(2,"Prison"), Genre(3,"Hope")),
    rating = 8.6,
    releaseDate = "1994-09-23",
    isFavourite = true
)


