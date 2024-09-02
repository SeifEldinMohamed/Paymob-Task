package com.example.paymobtaskmoviesapp.domain.usecase

import com.example.paymobtaskmoviesapp.domain.model.MovieDetailsDomainModel
import com.example.paymobtaskmoviesapp.domain.repository.MoviesRepository
import javax.inject.Inject

class InsertFavouriteMovieUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(movieDetailsDomainModel: MovieDetailsDomainModel) {
        return moviesRepository.insertFavouriteMovie(movieDetailsDomainModel)
    }

}