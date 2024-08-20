package com.vahitkeskin.jetpackcomposecleanmovieapp.domain.use_case.get_movie_detail

import coil.network.HttpException
import com.vahitkeskin.jetpackcomposecleanmovieapp.data.remote.dto.toMovieDetail
import com.vahitkeskin.jetpackcomposecleanmovieapp.domain.model.MovieDetail
import com.vahitkeskin.jetpackcomposecleanmovieapp.domain.repository.MovieRepository
import com.vahitkeskin.jetpackcomposecleanmovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    fun executeGetMovieDetails(imdbId: String): Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetail = repository.getMovieDetail(imdbId = imdbId)
            emit(Resource.Success(movieDetail.toMovieDetail()))
        } catch (e: IOError) {
            emit(Resource.Error(message = "No internet connection"))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error"))
        }
    }
}