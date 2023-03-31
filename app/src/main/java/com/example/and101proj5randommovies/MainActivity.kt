package com.example.and101proj5randommovies

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers


class MainActivity : AppCompatActivity() {
    var movieImageURL = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var movieImage =  findViewById<ImageView>(R.id.movie_image)
        var movieButton = findViewById<Button>(R.id.movie_button)
        var movieTitle = findViewById<TextView>(R.id.movie_title)
        var movieGenre = findViewById<TextView>(R.id.movie_genre)

        getMovieImageURL()
        Log.d("movieImageURL", "movie image URL set")

        getNextImage(movieButton, movieImage)
    }

    //figure out api https://imdb-api.com/api#Report-header
    //title https://imdb-api.com/en/API/Title/k_7o0ubhxp/tt1375666
    //poster https://imdb-api.com/en/API/Posters/k_7o0ubhxp/tt1375666
    //genre?
    //wikipedia https://imdb-api.com/en/API/Wikipedia/k_7o0ubhxp/tt1375666
    private fun getMovieImageURL() {
        val client = AsyncHttpClient()

        client["https://imdb-api.com/en/API/Posters/k_7o0ubhxp/tt1375666", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.d("Movie", "response successful")

                val posters = json.jsonObject.getJSONArray("posters")
                if(posters.length()>0){
                    movieImageURL = posters.getJSONObject(0).getString("")
                } else{
                    movieImageURL = ""
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Movie Error", errorResponse)
            }
        }]

    }

    private fun getNextImage(button: Button, imageView: ImageView) {
        button.setOnClickListener {
            getMovieImageURL()
            Glide.with(this)
                .load(movieImageURL)
                .fitCenter()
                .into(imageView)
        }
    }


}