package com.example.firebasemovies.data.repository

import com.example.firebasemovies.data.models.Movie
import com.example.firebasemovies.data.network.Resource

interface MoviesRepository {
    suspend fun getPopularMovies(): Resource<List<Movie>>
}