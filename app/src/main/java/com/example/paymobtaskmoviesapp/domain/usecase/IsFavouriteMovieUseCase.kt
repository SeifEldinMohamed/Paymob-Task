package com.example.paymobtaskmoviesapp.domain.usecase

import com.example.paymobtaskmoviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsFavouriteMovieUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    operator fun invoke(movieId: Int): Flow<Boolean> {
        return moviesRepository.isFavouriteMovie(movieId)
    }
}