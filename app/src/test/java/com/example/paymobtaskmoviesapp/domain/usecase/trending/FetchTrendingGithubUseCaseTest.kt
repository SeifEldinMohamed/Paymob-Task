package com.example.paymobtaskmoviesapp.domain.usecase.trending

import com.example.paymobtaskmoviesapp.domain.model.CustomApiExceptionDomainModel
import com.example.paymobtaskmoviesapp.domain.repository.MoviesRepository
import com.example.paymobtaskmoviesapp.domain.usecase.FetchPopularMovieListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class FetchTrendingGithubUseCaseTest {
    private lateinit var moviesRepository: MoviesRepository
    private lateinit var fetchPopularMovieListUseCase: FetchPopularMovieListUseCase

    @Before
    fun setup() {
        moviesRepository = mockk()
        fetchPopularMovieListUseCase = FetchPopularMovieListUseCase(moviesRepository)
    }

    @Test
    fun `when invoke is called, then it should return a list of TrendingGithubDomainModel`() =
        runBlocking {
            // given
            coEvery { moviesRepository.fetchPopularMovies() } returns fakePopularMoviesLazyPagingDomainModel

            // when
            val result = fetchPopularMovieListUseCase()

            // then
            assertEquals(fakePopularMoviesLazyPagingDomainModel, result)
        }


    @Test(expected = CustomApiExceptionDomainModel.NetworkExceptionDomainModel::class)
    fun `when repository throws an NetworkExceptionDomainModel, then invoke should throw the same exception`() {
        runBlocking {
            // given
            coEvery { moviesRepository.fetchPopularMovies() } throws CustomApiExceptionDomainModel.NetworkExceptionDomainModel

            // when
            fetchPopularMovieListUseCase()
        }
    }

    @Test(expected = CustomApiExceptionDomainModel.NoInternetConnectionExceptionDomainModel::class)
    fun `when repository throws an NoInternetConnectionExceptionDomainModel, then invoke should throw the same exception`() {
        runBlocking {
            // given
            coEvery { moviesRepository.fetchPopularMovies() } throws CustomApiExceptionDomainModel . NoInternetConnectionExceptionDomainModel

                    // when
                    fetchPopularMovieListUseCase()
        }
    }

    @Test(expected = CustomApiExceptionDomainModel.TimeoutExceptionDomainModel::class)
    fun `when repository throws an TimeoutExceptionDomainModel, then invoke should throw the same exception`() {
        runBlocking {
            // given
            coEvery { moviesRepository.fetchPopularMovies() } throws CustomApiExceptionDomainModel.TimeoutExceptionDomainModel

            // when
            fetchPopularMovieListUseCase()
        }
    }

    @Test(expected = CustomApiExceptionDomainModel.ServiceNotFoundExceptionDomainModel::class)
    fun `when repository throws an ServiceNotFoundExceptionDomainModel, then invoke should throw the same exception`() {
        runBlocking {
            // given
            coEvery { moviesRepository.fetchPopularMovies() } throws CustomApiExceptionDomainModel.ServiceNotFoundExceptionDomainModel

            // when
            fetchPopularMovieListUseCase()
        }
    }

    @Test(expected = CustomApiExceptionDomainModel.AccessDeniedExceptionDomainModel::class)
    fun `when repository throws an AccessDeniedExceptionDomainModel, then invoke should throw the same exception`() {
        runBlocking {
            // given
            coEvery { moviesRepository.fetchPopularMovies() } throws CustomApiExceptionDomainModel.AccessDeniedExceptionDomainModel

            // when
            fetchPopularMovieListUseCase()
        }
    }

    @Test(expected = CustomApiExceptionDomainModel.ServiceUnavailableExceptionDomainModel::class)
    fun `when repository throws an ServiceUnavailableExceptionDomainModel, then invoke should throw the same exception`() {
        runBlocking {
            // given
            coEvery { moviesRepository.fetchPopularMovies() } throws CustomApiExceptionDomainModel.ServiceUnavailableExceptionDomainModel

            // when
            fetchPopularMovieListUseCase()
        }
    }

    @Test(expected = CustomApiExceptionDomainModel.UnknownExceptionDomainModel::class)
    fun `when repository throws an UnknownExceptionDomainModel, then invoke should throw the same exception`() {
        runBlocking {
            // given
            coEvery { moviesRepository.fetchPopularMovies() } throws CustomApiExceptionDomainModel.UnknownExceptionDomainModel

            // when
            fetchPopularMovieListUseCase()
        }
    }
}