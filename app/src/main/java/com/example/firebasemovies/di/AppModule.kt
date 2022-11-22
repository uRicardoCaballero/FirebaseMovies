package com.example.firebasemovies.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import com.example.firebasemovies.data.network.TmdbHttpClient
import com.example.firebasemovies.data.repository.MoviesRepository
import com.example.firebasemovies.data.repository.MoviesRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun getHttpClient(httpClient: TmdbHttpClient): HttpClient = httpClient.getHttpClient()

    @Provides
    fun getMoviesRepository(impl: MoviesRepositoryImpl): MoviesRepository = impl
}