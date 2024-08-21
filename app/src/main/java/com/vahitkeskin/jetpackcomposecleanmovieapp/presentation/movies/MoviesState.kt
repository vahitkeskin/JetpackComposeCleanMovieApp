package com.vahitkeskin.jetpackcomposecleanmovieapp.presentation.movies

import com.vahitkeskin.jetpackcomposecleanmovieapp.domain.model.Movie

data class MoviesState(
    val isLoading: Boolean = false,
    val movie: List<Movie> = emptyList(),
    val error: String = "",
    val search: String = "batman"
)
