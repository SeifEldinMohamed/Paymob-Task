package com.example.paymobtaskmoviesapp.domain.usecase.trending

import androidx.paging.PagingData
import com.example.paymobtaskmoviesapp.domain.model.PopularMovieDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


private val fakePopularMovieDomainModel = PopularMovieDomainModel(
    id = 12345,
    title = "The Shawshank Redemption",
    overview = "A classic prison escape story.",
    posterPath = "https://image.tmdb.org/t/p/w500/q6y0Go1POy89lB7qAUcKc59FH.jpg",
    voteAverage = 8.6,
    releaseDate = "1994-09-23",
    originalLanguage = "en"
)

private val fakePopularMoviesListDomainModel= listOf(
    fakePopularMovieDomainModel,
)


val fakePopularMoviesLazyPagingDomainModel = createFakePopularMoviesLazyPagingItemsDomainModel()
private fun createFakePopularMoviesLazyPagingItemsDomainModel(): Flow<PagingData<PopularMovieDomainModel>> {
    return flowOf(PagingData.from(fakePopularMoviesListDomainModel))
}


