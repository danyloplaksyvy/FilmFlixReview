package ua.digijed.filmflixreview.model.repository

import retrofit2.Call
import ua.digijed.filmflixreview.data.Movies
import ua.digijed.filmflixreview.data.MoviesDetails

interface MoviesDBRepository {
    fun getMovies() : Call<Movies>

    fun getMovieDetails(id: Int) : Call<MoviesDetails>
}