package ua.digijed.filmflixreview.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ua.digijed.filmflixreview.data.Movies
import ua.digijed.filmflixreview.data.Result
import ua.digijed.filmflixreview.data.MoviesDetails
import ua.digijed.filmflixreview.model.repository.MoviesDBRepository
import ua.digijed.filmflixreview.model.repository.MoviesDBRepositoryImpl

class MoviesViewModel {

    private val _movies = MutableLiveData<List<Result?>>()
    val movies : LiveData<List<Result?>> = _movies

    private val _movieDetails = MutableLiveData<MoviesDetails?>()
    val moviesDetails : LiveData<MoviesDetails?> = _movieDetails

    private val mMoviesRepository : MoviesDBRepository = MoviesDBRepositoryImpl()

    fun getMovies(){
        val response = mMoviesRepository.getMovies()
        response.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>?, response: Response<Movies>?){
                _movies.postValue(response?.body()?.results)
            }
            override fun onFailure(call: Call<Movies>?, t: Throwable?){
                Log.d("testLogs","onFailure : ${t?.message}")
            }
        })
    }

    fun getMovieDetails(id: Int){
        val response = mMoviesRepository.getMovieDetails(id)
        response.enqueue(object : Callback<MoviesDetails> {
            override fun onResponse(call: Call<MoviesDetails>?, response: Response<MoviesDetails>?){
                _movieDetails.postValue(response?.body())
            }
            override fun onFailure(call: Call<MoviesDetails>?, t: Throwable?){
                Log.d("testLogs","onFailure : ${t?.message}")
            }
        })
    }
}