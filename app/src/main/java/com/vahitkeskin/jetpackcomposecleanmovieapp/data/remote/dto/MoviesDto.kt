package com.vahitkeskin.jetpackcomposecleanmovieapp.data.remote.dto

data class MoviesDto(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)