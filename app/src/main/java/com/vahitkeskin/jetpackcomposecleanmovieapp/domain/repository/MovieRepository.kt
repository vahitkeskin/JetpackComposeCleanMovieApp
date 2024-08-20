package com.vahitkeskin.jetpackcomposecleanmovieapp.domain.repository

import com.vahitkeskin.jetpackcomposecleanmovieapp.data.remote.dto.MovieDetailDto
import com.vahitkeskin.jetpackcomposecleanmovieapp.data.remote.dto.MoviesDto

interface MovieRepository {

    suspend fun getMovies(search: String): MoviesDto
    suspend fun getMovieDetail(imdbId: String): MovieDetailDto

}