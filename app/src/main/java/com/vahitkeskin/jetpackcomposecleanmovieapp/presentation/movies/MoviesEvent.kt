package com.vahitkeskin.jetpackcomposecleanmovieapp.presentation.movies

sealed class MoviesEvent {
    data class Search(val searchString: String) : MoviesEvent()
}