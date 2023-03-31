package com.example.and101proj5randommovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var movieButton = findViewById<Button>(R.id.movie_button)
        var movieTitle = findViewById<TextView>(R.id.movie_title)
        var movieGenre = findViewById<TextView>(R.id.movie_genre)
    }

    //figure out api https://imdb-api.com/api#Report-header
    //title https://imdb-api.com/en/API/Title/k_7o0ubhxp/tt1375666
    //poster https://imdb-api.com/en/API/Posters/k_7o0ubhxp/tt1375666
    //genre?
    //wikipedia https://imdb-api.com/en/API/Wikipedia/k_7o0ubhxp/tt1375666
    private fun getMovieImageURL() {
        val client = AsyncHttpClient()

        client["https://imdb-api.com/en/API/Posters/k_7o0ubhxp/tt1375666", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Dog", "response successful")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Dog Error", errorResponse)
            }
        }]

    }
}