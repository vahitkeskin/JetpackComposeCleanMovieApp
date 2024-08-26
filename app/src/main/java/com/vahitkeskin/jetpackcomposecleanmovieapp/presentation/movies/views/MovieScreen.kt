package com.vahitkeskin.jetpackcomposecleanmovieapp.presentation.movies.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vahitkeskin.jetpackcomposecleanmovieapp.presentation.Screen
import com.vahitkeskin.jetpackcomposecleanmovieapp.presentation.movies.MoviesEvent
import com.vahitkeskin.jetpackcomposecleanmovieapp.presentation.movies.MoviesState
import com.vahitkeskin.jetpackcomposecleanmovieapp.presentation.movies.MoviesViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MovieScreen(
    navController: NavController,
    viewModel: MoviesViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    var job: Job? = null
    val lifecycleScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column {
            MovieSearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                hint = "Batman",
                onSearch = {
                    viewModel.onEvent(MoviesEvent.Search(it))
                },
                onMomentarySearch = {
                    job?.cancel()
                    job = lifecycleScope.launch {
                        delay(1000)
                        if (it.isNotEmpty()) {
                            viewModel.onEvent(MoviesEvent.Search(it))
                        }
                    }
                }
            )

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(3),
                verticalItemSpacing = 4.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                content = {
                    items(state.movie) { movie ->
                        MovieListRow(movie = movie, onItemClick = {
                            navController.navigate(Screen.MovieDetailScreen.route + "/${movie.imdbID}")
                        })
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }

}

@Composable
fun MovieSearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {},
    onMomentarySearch: (String) -> Unit = {},
) {
    var text by remember {
        mutableStateOf("")
    }

    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    val keyboardController = LocalSoftwareKeyboardController.current


    Box(modifier = modifier) {
        TextField(
            value = text,
            onValueChange = {
                text = it
                onMomentarySearch.invoke(it)
            },
            keyboardActions = KeyboardActions(onDone = {
                onSearch.invoke(text) // keyboardActions -> Ok button
                keyboardController?.hide() // keyboardController -> Hide keyboard
            }),
            maxLines = 1,
            textStyle = TextStyle(color = Color.Black),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.White,
                focusedIndicatorColor = Color.White,
                cursorColor = Color.Black,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White //BG Color
            ),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true && text.isEmpty()
                },
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences
            ),
            trailingIcon = {
                if (text.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            keyboardController?.hide()
                            text = MoviesState().search
                            onSearch.invoke(text)
                        }) {
                        Icon(
                            modifier = Modifier
                                .padding(10.dp)
                                .clip(CircleShape)
                                .background(Color.LightGray),
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear text",
                            tint = Color.Gray
                        )
                    }
                }
            }
        )

        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }

}

@Preview
@Composable
fun MovieScreenPreview() {
    MovieScreen(navController = rememberNavController())
}