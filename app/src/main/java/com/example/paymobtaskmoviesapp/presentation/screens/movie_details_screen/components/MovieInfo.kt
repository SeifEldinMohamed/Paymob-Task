package com.example.paymobtaskmoviesapp.presentation.screens.movie_details_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.paymobtaskmoviesapp.R
import com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.datamodel.movie_details.Genre
import com.example.paymobtaskmoviesapp.presentation.screens.movie_details_screen.preview_data.fakeMovieDetailsUiModel
import com.example.paymobtaskmoviesapp.presentation.theme.LightRed

@Composable
fun MovieInfo(
    modifier: Modifier = Modifier,
    movieDetails: String,
    movieReleaseDate: String,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = movieDetails,
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                    append(stringResource(R.string.release_date))
                }
                append(stringResource(R.string.column))
                append(movieReleaseDate)
            },
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Categories(
    categories: List<Genre>,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier,
    ) {
        for (category in categories) {
            Box(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .background(
                        LightRed.copy(.2f),
                        MaterialTheme.shapes.small
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 2.dp),
                    text = category.name,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCategories() {
    Categories(categories = fakeMovieDetailsUiModel.categories)
}

@Preview(showBackground = true)
@Composable
private fun PreviewMovieInfo() {
    MovieInfo(movieDetails = fakeMovieDetailsUiModel.details, movieReleaseDate = fakeMovieDetailsUiModel.releaseDate)
}