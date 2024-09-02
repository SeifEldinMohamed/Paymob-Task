package com.example.paymobtaskmoviesapp.domain.usecase

import com.example.paymobtaskmoviesapp.domain.model.MovieDetailsDomainModel
import com.example.paymobtaskmoviesapp.domain.repository.MoviesRepository
import javax.inject.Inject

class FetchMovieDetailsUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(movieId: Int): MovieDetailsDomainModel {
        return moviesRepository.fetchMovieDetails(movieId)
    }

}