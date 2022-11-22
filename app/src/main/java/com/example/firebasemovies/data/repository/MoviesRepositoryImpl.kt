package com.example.firebasemovies.data.repository

import io.ktor.client.*
import io.ktor.client.request.*
import com.example.firebasemovies.data.models.Movie
import com.example.firebasemovies.data.models.PopularMovies
import com.example.firebasemovies.data.network.BASE_URL
import com.example.firebasemovies.data.network.Resource
import javax.inject.Inject

private const val POPULAR_MOVIES = "${BASE_URL}/popular"

class MoviesRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
) : MoviesRepository {

    override suspend fun getPopularMovies(): Resource<List<Movie>> {
        return try {
            Resource.Success(
                httpClient.get<PopularMovies> {
                    url(POPULAR_MOVIES)
                }.movies
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }
}