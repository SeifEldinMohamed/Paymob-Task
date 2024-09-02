package com.example.paymobtaskmoviesapp.domain.usecase.details


import com.example.domain.usecase.details.fakeDetailsDomainModel
import com.example.paymobtaskmoviesapp.data.repository.MoviesRepositoryImpl
import com.example.paymobtaskmoviesapp.domain.model.CustomApiExceptionDomainModel
import com.example.paymobtaskmoviesapp.domain.usecase.FetchMovieDetailsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FetchDetailsUseCaseTest {
    private lateinit var moviesRepositoryImpl: MoviesRepositoryImpl
    // CUT
    private lateinit var fetchMovieDetailsUseCase: FetchMovieDetailsUseCase

    @Before
    fun setup() {
        moviesRepositoryImpl = mockk()
        fetchMovieDetailsUseCase = FetchMovieDetailsUseCase(moviesRepositoryImpl)
    }
    /**
     * 1. invoke called
     * 2. in case of success, should return repositoryDetailsDomainModel
     **/
    @Test
    fun `when invoke is called, then it should return repositoryDetailsDomainModel`() =
        runBlocking {
            // given
            coEvery { moviesRepositoryImpl.fetchMovieDetails(any())} returns fakeDetailsDomainModel

            // when
            val result = fetchMovieDetailsUseCase(3223)

            // then
            assertEquals(fakeDetailsDomainModel, result)
        }

    /**
     * 1. invoke called
     * 2. in case of Failure, should throw NetworkExceptionDomainModel
     **/
    @Test(expected = CustomApiExceptionDomainModel.NetworkExceptionDomainModel::class)
    fun `when repository throws an NetworkExceptionDomainModel, then invoke should throw the same exception`() {
        runBlocking {
            // given
            coEvery { moviesRepositoryImpl.fetchMovieDetails(any()) } throws CustomApiExceptionDomainModel.NetworkExceptionDomainModel

            // when
            fetchMovieDetailsUseCase(3223)
        }
    }
}