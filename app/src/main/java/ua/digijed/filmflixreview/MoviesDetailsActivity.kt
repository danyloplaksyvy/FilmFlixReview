package ua.digijed.filmflixreview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import ua.digijed.filmflixreview.apis.ApiInterface
import ua.digijed.filmflixreview.data.MoviesDetails

class MoviesDetailsActivity : AppCompatActivity() {
    private lateinit var title: TextView
    private lateinit var releaseDate: TextView
    private lateinit var score: TextView
    private lateinit var overview: TextView
    private lateinit var banner: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_details)
        val id = intent.getIntExtra("id", 0)
        Log.d("testing", "id $id")

        title = findViewById(R.id.movies_details_title)
        releaseDate = findViewById(R.id.movies_details_date)
        score = findViewById(R.id.movies_details_score)
        overview = findViewById(R.id.movies_details_body_overview)
//        17b7911e7f2f6203fd7d777a337fd393
//        5339b0f167df7c2e6063fab4895fcd00
        val apiInterface = id?.let {
            ApiInterface.create().getMovieDetails(it, "17b7911e7f2f6203fd7d777a337fd393")
        }
        apiInterface?.enqueue(object : Callback<MoviesDetails> {
            override fun onResponse(
                call: Call<MoviesDetails>?,
                response: Response<MoviesDetails>?
            ) {
                title.text = response?.body()?.title
                releaseDate.text = response?.body()?.release_date.toString()
                score.text = response?.body()?.vote_average.toString()
                overview.text = response?.body()?.overview
                banner = findViewById(R.id.movies_details_image_banner)
                Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500" + response?.body()?.backdrop_path)
                    .into(banner)

            }

            override fun onFailure(call: Call<MoviesDetails>?, t: Throwable?) {
                Log.d("testlogs", "onFailure : ${t?.message}")
            }
        })
    }
}