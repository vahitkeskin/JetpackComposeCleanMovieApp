package com.vahitkeskin.jetpackcomposecleanmovieapp.data.remote.dto

import com.vahitkeskin.jetpackcomposecleanmovieapp.domain.model.Movie

data class MoviesDto(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)

fun MoviesDto.toMovieList(): List<Movie> {
    return Search.map { search -> Movie(search.Poster, search.Title, search.Year, search.imdbID) }
}