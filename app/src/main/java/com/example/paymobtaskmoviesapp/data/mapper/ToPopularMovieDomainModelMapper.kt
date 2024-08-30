package com.example.paymobtaskmoviesapp.data.mapper

import com.example.paymobtaskmoviesapp.data.Constants.Companion.IMAGE_BASE_URL
import com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.datamodel.movies_list.MovieDataModel
import com.example.paymobtaskmoviesapp.domain.model.PopularMovieDomainModel
fun MovieDataModel.toMovieDomainModel(): PopularMovieDomainModel {
    return PopularMovieDomainModel(
        id = this.id,
        title = this.title,
        posterPath = IMAGE_BASE_URL + this.poster_path,
        releaseDate = this.release_date,
        voteAverage = this.vote_average,
        overview = this.overview,
        originalLanguage = this.original_language
    )
}