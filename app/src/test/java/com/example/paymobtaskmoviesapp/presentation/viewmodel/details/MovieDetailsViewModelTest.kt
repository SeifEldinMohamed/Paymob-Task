package com.example.paymobtaskmoviesapp.presentation.viewmodel.details

import app.cash.turbine.test
import com.example.paymobtaskmoviesapp.domain.model.CustomApiExceptionDomainModel
import com.example.paymobtaskmoviesapp.domain.model.MovieDetailsDomainModel
import com.example.paymobtaskmoviesapp.domain.usecase.DeleteFavouriteMovieUseCase
import com.example.paymobtaskmoviesapp.domain.usecase.FetchMovieDetailsUseCase
import com.example.paymobtaskmoviesapp.domain.usecase.InsertFavouriteMovieUseCase
import com.example.paymobtaskmoviesapp.domain.usecase.IsFavouriteMovieUseCase
import com.example.paymobtaskmoviesapp.presentation.mapper.toCustomApiExceptionUiModel
import com.example.paymobtaskmoviesapp.presentation.mapper.toMovieDetailsUIModel
import com.example.paymobtaskmoviesapp.presentation.screens.movie_details_screen.MovieDetailsUiState
import com.example.paymobtaskmoviesapp.presentation.screens.movie_details_screen.model.MovieDetailsUiModel
import com.example.paymobtaskmoviesapp.presentation.screens.movie_details_screen.viewmodel.MovieDetailsViewModel
import com.example.paymobtaskmoviesapp.presentation.utils.MainCoroutineRule
import com.example.paymobtaskmoviesapp.presentation.utils.TestDispatchersImpl
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDetailsViewModelTest {

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel
    private lateinit var fetchMovieDetailsUseCase: FetchMovieDetailsUseCase
    private lateinit var insertFavouriteMovieUseCase: InsertFavouriteMovieUseCase
    private lateinit var deleteFavouriteMovieUseCase: DeleteFavouriteMovieUseCase
    private lateinit var isFavouriteMovieUseCase: IsFavouriteMovieUseCase
    private lateinit var testDispatcher: TestDispatchersImpl

    @get:Rule
    val mainDispatcherRule = MainCoroutineRule()

    @Before
    fun setup() {
        testDispatcher = TestDispatchersImpl()
        fetchMovieDetailsUseCase = mockk(relaxed = true)
        insertFavouriteMovieUseCase = mockk(relaxed = true)
        deleteFavouriteMovieUseCase = mockk(relaxed = true)
        isFavouriteMovieUseCase = mockk(relaxed = true)
        movieDetailsViewModel = MovieDetailsViewModel(
            fetchMovieDetailsUseCase,
            insertFavouriteMovieUseCase,
            deleteFavouriteMovieUseCase,
            isFavouriteMovieUseCase,
            testDispatcher
        )

    }

    @Test
    fun `requestMovieDetails(), when successful call, then should emit MovieDetailsUiState with loading then MovieDetailsUiModel`() = runTest {

        // Given
        val fakeDetailsDomainModel = mockk<MovieDetailsDomainModel>()
        val fakeDetailsUiModel = mockk<MovieDetailsUiModel>()
        coEvery { fetchMovieDetailsUseCase(any()) } returns fakeDetailsDomainModel
        every { fakeDetailsDomainModel.toMovieDetailsUIModel() } returns fakeDetailsUiModel

        // When
        movieDetailsViewModel.requestMovieDetails(1)

        // Then
        movieDetailsViewModel.movieDetailsUiStateFlow.test {
            assertEquals(MovieDetailsUiState(isLoading = true), awaitItem())
            assertEquals(
                MovieDetailsUiState(isLoading = false, movieDetailsUiModel = fakeDetailsUiModel),
                awaitItem()
            )
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `requestMovieDetails(), when exception, then it should emit error state`() = runTest {
        // Given
        val customException = CustomApiExceptionDomainModel.NetworkExceptionDomainModel
        coEvery { fetchMovieDetailsUseCase(any()) } throws customException

        // When
        movieDetailsViewModel.requestMovieDetails(1)

        // Then
        movieDetailsViewModel.movieDetailsUiStateFlow.test {
            assertEquals(MovieDetailsUiState(isLoading = true), awaitItem())
            advanceUntilIdle()
            assertEquals(
                MovieDetailsUiState(
                    isLoading = false,
                    isError = true,
                    customApiErrorExceptionUiModel = customException.toCustomApiExceptionUiModel()
                ),
                awaitItem()
            )
            cancelAndConsumeRemainingEvents()
        }
    }
}