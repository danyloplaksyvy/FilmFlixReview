package ua.digijed.filmflixreview.model.repository

import retrofit2.Call
import ua.digijed.filmflixreview.data.Movies
import ua.digijed.filmflixreview.data.MoviesDetails
import ua.digijed.filmflixreview.model.apis.ApiInterface

class MoviesDBRepositoryImpl : MoviesDBRepository{
    private val apiInterface = ApiInterface.create()

    override fun getMovies() : Call<Movies> {
        return apiInterface.getMovies("17b7911e7f2f6203fd7d777a337fd393","en-US",1)
    }

    override fun getMovieDetails(id: Int): Call<MoviesDetails> {
        return apiInterface.getMovieDetails(id,"17b7911e7f2f6203fd7d777a337fd393")
    }
}