package ua.digijed.filmflixreview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ua.digijed.filmflixreview.apis.ApiInterface
import ua.digijed.filmflixreview.data.ItemsViewModel
import ua.digijed.filmflixreview.data.Movies

@Suppress("DEPRECATION")
class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = GridLayoutManager(this,2)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemsViewModel(R.drawable.ic_launcher_foreground, "Item " + i))
        }


        val apiInterface = ApiInterface.create().getMovies("17b7911e7f2f6203fd7d777a337fd393")

        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue( object : Callback<Movies>, CustomAdapter.ItemClickListener {
            override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {
                Log.d("testLogs","OnResponse Success ${call.toString()} ${response?.body()?.results}")
                // This will pass the ArrayList to our Adapter
                val adapter = CustomAdapter(response?.body()?.results, this)

                // Setting the Adapter with the recyclerview
                recyclerview.adapter = adapter
            }

            override fun onFailure(call: Call<Movies>?, t: Throwable?) {
                Log.d("testLogs","OnFailure : ${t?.message}")
            }

            override fun onItemClick(id: Int) {
                val intent = Intent(this@MoviesActivity, MoviesDetailsActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
    }
}