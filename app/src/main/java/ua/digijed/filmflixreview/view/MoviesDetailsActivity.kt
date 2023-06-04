package ua.digijed.filmflixreview.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import ua.digijed.filmflixreview.viewmodel.MoviesViewModel
import ua.digijed.filmflixreview.R
import ua.digijed.filmflixreview.data.MoviesDetails

class MoviesDetailsActivity : AppCompatActivity() {
    private val mViewModel: MoviesViewModel = MoviesViewModel()

    private lateinit var mTitle: TextView
    private lateinit var mReleaseDate: TextView
    private lateinit var mScore: TextView
    private lateinit var mOverview: TextView
    private lateinit var mBanner: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_details)

        val id = intent.getIntExtra("id", 0)
        initViews()
        initObservers()
        mViewModel.getMovieDetails(id)

    }

    private fun initObservers() {
        mViewModel.apply {
            moviesDetails.observe(this@MoviesDetailsActivity) {
                setMovieInformation(it)
            }
        }
    }

    private fun setMovieInformation(moviesDetails: MoviesDetails?) {
        mTitle.text = moviesDetails?.title
        mReleaseDate.text = moviesDetails?.release_date.toString()
        mScore.text = moviesDetails?.vote_average.toString()
        mOverview.text = moviesDetails?.overview
        mBanner = findViewById(R.id.movies_details_image_banner)
        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500" + moviesDetails?.backdrop_path)
            .into(mBanner)
    }

    private fun initViews() {
        mTitle = findViewById(R.id.movies_details_title)
        mReleaseDate = findViewById(R.id.movies_details_date)
        mScore = findViewById(R.id.movies_details_score)
        mOverview = findViewById(R.id.movies_details_body_overview)
        mBanner = findViewById(R.id.movies_details_image_banner)
    }
}
