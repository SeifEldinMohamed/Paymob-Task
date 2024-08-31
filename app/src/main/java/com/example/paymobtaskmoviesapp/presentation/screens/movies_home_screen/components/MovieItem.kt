package com.example.paymobtaskmoviesapp.presentation.screens.movies_home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.paymobtaskmoviesapp.presentation.screens.movies_home_screen.model.PopularMovieUiModel
import com.example.paymobtaskmoviesapp.presentation.screens.movies_home_screen.preview_data.fakePopularMovieUiModel
import com.example.paymobtaskmoviesapp.presentation.theme.PaymobTaskMoviesAppTheme
import com.example.paymobtaskmoviesapp.presentation.theme.Red

@Composable
fun PopularMovieItem(
    popularMovieUiModel: PopularMovieUiModel,
    isFavorite: Boolean,
    onMovieClick: (id: Int) -> Unit,
    onFavoriteClick: (id: Int) -> Unit
) {
    val context = LocalContext.current
    val movieImagePosterFadeDuration = 1000
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth()
            .clickable { onMovieClick(popularMovieUiModel.id) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            Modifier.padding(16.dp)
        ) {
            Image(
                painter =
                rememberAsyncImagePainter(
                    ImageRequest.Builder(context)
                        .data(data = popularMovieUiModel.posterPath)
                        .crossfade(movieImagePosterFadeDuration)
                        .build(),
                ),
                contentDescription = null,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.large)
                    .width(110.dp)
                    .height(150.dp),
            )

            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = popularMovieUiModel.title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = "Rating: ${popularMovieUiModel.voteAverage}",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Release Date: ${popularMovieUiModel.releaseDate}",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (isFavorite) {
                    IconButton(
                        onClick = { onFavoriteClick(popularMovieUiModel.id) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite",
                            tint = Red,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFEBEBEB)
@Composable
private fun PreviewPopularMovieItem() {
    PopularMovieItem(
        popularMovieUiModel = fakePopularMovieUiModel,
        isFavorite = true,
        onMovieClick = {}
    ) {

    }
}