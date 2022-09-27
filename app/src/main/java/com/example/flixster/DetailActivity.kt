package com.example.flixster

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import java.net.URL


private const val name = "DetailActivity"
private const val detail_url = "https://api.themoviedb.org/3/movie/%d?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"

class DetailActivity : AppCompatActivity() {

    private lateinit var dtTitle: TextView
    private lateinit var dtPoster: ImageView
    private lateinit var dtOverview: TextView
    private lateinit var dtReleaseDate: TextView
    private lateinit var dtRatingBar: RatingBar
    private lateinit var dtBudget: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        dtTitle = findViewById(R.id.dtTitle)
        dtPoster = findViewById(R.id.dtPoster)
        dtOverview = findViewById(R.id.dtOverview)
        dtReleaseDate = findViewById(R.id.dtReleaseDate)
        dtBudget = findViewById(R.id.dtBudget)
        dtRatingBar = findViewById(R.id.dtRatingBar)

        val movie = intent.getParcelableExtra<Movie>(movieDetail) as Movie
        dtTitle.text = movie.title
        dtOverview.text = movie.overview

        Glide.with(this).load(movie.imageUrl).into(dtPoster)


        val client = AsyncHttpClient()
        client.get(detail_url.format(movie.movieId), object: JsonHttpResponseHandler(){
            override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?){
                Log.e(name, "On Failure $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON){
                val dataInfo = json.jsonObject
                var avgD = dataInfo.getDouble("vote_average")
                dtRatingBar.rating = avgD.toFloat()
                val budgetInt = dataInfo.getInt("budget").toString()
                dtBudget.text = "Budget: $$budgetInt"
                dtReleaseDate.text = "Release date: ${dataInfo.getString("release_date")}"
            }

        })

    }
}