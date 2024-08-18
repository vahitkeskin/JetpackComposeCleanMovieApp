package com.vahitkeskin.jetpackcomposecleanmovieapp.data.remote

import com.vahitkeskin.jetpackcomposecleanmovieapp.data.remote.dto.MovieDetailDto
import com.vahitkeskin.jetpackcomposecleanmovieapp.data.remote.dto.MoviesDto
import com.vahitkeskin.jetpackcomposecleanmovieapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    //https://www.omdbapi.com/?apikey=e902f70b&s=batman
    //https://www.omdbapi.com/?apikey=e902f70b&i=tt0372784

    @GET(".")
    suspend fun getMovies(
        @Query("s") searchString: String,
        @Query("apikey") apiKey: String = API_KEY
    ) : MoviesDto

    @GET(".")
    suspend fun getMovieDetail(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey: String = API_KEY
    ) : MovieDetailDto

}