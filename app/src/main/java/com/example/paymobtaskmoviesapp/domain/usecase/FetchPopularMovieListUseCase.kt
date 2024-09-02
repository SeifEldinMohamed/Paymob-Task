package com.example.paymobtaskmoviesapp.domain.usecase

import androidx.paging.PagingData
import com.example.paymobtaskmoviesapp.domain.model.PopularMovieDomainModel
import com.example.paymobtaskmoviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPopularMovieListUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    operator fun invoke(): Flow<PagingData<PopularMovieDomainModel>> = moviesRepository.fetchPopularMovies()

}