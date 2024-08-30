package com.example.paymobtaskmoviesapp.domain.repository

import androidx.paging.PagingData
import com.example.paymobtaskmoviesapp.domain.model.PopularMovieDomainModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun fetchPopularMovies(): Flow<PagingData<PopularMovieDomainModel>>
}