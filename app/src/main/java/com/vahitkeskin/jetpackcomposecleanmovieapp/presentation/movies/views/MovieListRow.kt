package com.vahitkeskin.jetpackcomposecleanmovieapp.presentation.movies.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.vahitkeskin.jetpackcomposecleanmovieapp.domain.model.Movie

@Composable
fun MovieListRow(movie: Movie, onItemClick: (Movie) -> Unit) {
    Card(
        modifier = Modifier
            .clickable { onItemClick(movie) }
            .size(200.dp)
            .padding(5.dp)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(15.dp),
                spotColor = Color.DarkGray
            )
            .padding(5.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberImagePainter(data = movie.Poster),
                contentDescription = movie.Title,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent, Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )
            Column(
                modifier = Modifier.align(Alignment.BottomStart).padding(horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = movie.Title,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = movie.Year,
                    style = MaterialTheme.typography.titleSmall,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}