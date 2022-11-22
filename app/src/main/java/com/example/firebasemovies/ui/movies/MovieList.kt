package com.example.firebasemovies.ui.movies

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.firebasemovies.data.models.Movie

@Composable
fun MovieList(movies: List<Movie>) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            items(movies) { item ->
                MovieItem(item)
            }
        }
    }
}