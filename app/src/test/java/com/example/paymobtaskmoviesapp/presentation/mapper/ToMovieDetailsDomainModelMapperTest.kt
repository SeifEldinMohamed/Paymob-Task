package com.example.paymobtaskmoviesapp.presentation.mapper


import com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.datamodel.movie_details.Genre
import com.example.paymobtaskmoviesapp.domain.model.MovieDetailsDomainModel
import com.example.paymobtaskmoviesapp.presentation.screens.movie_details_screen.model.MovieDetailsUiModel
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
class ToMovieDetailsDomainModelMapperTest(
    private val inputData: MovieDetailsUiModel,
    private val expectedOutput: MovieDetailsDomainModel
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: InputModel = {0}, Expected = {1}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(
                    MovieDetailsUiModel(
                        id = 1,
                        name = "Inception",
                        image = "path/to/image.jpg",
                        releaseDate = "2010-07-16",
                        rating = 8.8,
                        details = "A mind-bending thriller",
                        categories = listOf(Genre(1,"Sci-Fi"), Genre(1,"Thriller")),
                        isFavourite = true
                    ),
                    MovieDetailsDomainModel(
                        id = 1,
                        name = "Inception",
                        posterPath = "path/to/image.jpg",
                        releaseDate = "2010-07-16",
                        rating = 8.8,
                        details = "A mind-bending thriller",
                        categories = listOf(Genre(1,"Sci-Fi"), Genre(1,"Thriller")),
                        isFavourite = true
                    )
                ),
                arrayOf(
                    MovieDetailsUiModel(
                        id = 2,
                        name = "The Dark Knight",
                        image = "path/to/image2.jpg",
                        releaseDate = "2008-07-18",
                        rating = 9.0,
                        details = "A crime drama with a superhero twist",
                        categories =listOf(Genre(1,"Sci-Fi"), Genre(1,"Thriller")),
                        isFavourite = false
                    ),
                    MovieDetailsDomainModel(
                        id = 2,
                        name = "The Dark Knight",
                        posterPath = "path/to/image2.jpg",
                        releaseDate = "2008-07-18",
                        rating = 9.0,
                        details = "A crime drama with a superhero twist",
                        categories = listOf(Genre(1,"Sci-Fi"), Genre(1,"Thriller")),
                        isFavourite = false
                    )
                )
            )
        }
    }

    @Test
    fun `toMovieDetailsDomainModel() should map correctly from MovieDetailsUiModel to MovieDetailsDomainModel`() {
        // when
        val result = inputData.toMovieDetailsDomainModel()
        // then
        assertEquals(expectedOutput, result)
    }
}