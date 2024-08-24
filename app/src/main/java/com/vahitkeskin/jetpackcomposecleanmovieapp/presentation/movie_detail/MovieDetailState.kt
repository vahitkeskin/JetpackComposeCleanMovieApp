package com.vahitkeskin.jetpackcomposecleanmovieapp.presentation.movie_detail

import com.vahitkeskin.jetpackcomposecleanmovieapp.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movie: MovieDetail? = null,
    val error: String = ""
)