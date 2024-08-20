package com.vahitkeskin.jetpackcomposecleanmovieapp.data.repository

import com.vahitkeskin.jetpackcomposecleanmovieapp.data.remote.MovieAPI
import com.vahitkeskin.jetpackcomposecleanmovieapp.data.remote.dto.MovieDetailDto
import com.vahitkeskin.jetpackcomposecleanmovieapp.data.remote.dto.MoviesDto
import com.vahitkeskin.jetpackcomposecleanmovieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieAPI
) : MovieRepository {

    override suspend fun getMovies(search: String): MoviesDto {
        return api.getMovies(search)
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailDto {
        return api.getMovieDetail(imdbId)
    }
}