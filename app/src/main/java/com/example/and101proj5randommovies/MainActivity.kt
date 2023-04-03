package com.example.and101proj5randommovies

import android.os.Bundle
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
    //var posterPathArray = mutableListOf<String>()
    var movieImageURL = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getMovieImageURL()
        Log.d("movieImageURL", "movie image URL set")


        val movieImage =  findViewById<ImageView>(R.id.movie_image)
        val movieButton = findViewById<Button>(R.id.movie_button)
        val movieTitle = findViewById<TextView>(R.id.movie_title)
        val movieGenre = findViewById<TextView>(R.id.movie_genre)

       getNextImage(movieButton, movieImage)
    }
    private fun getMovieImageURL() {
        val client = AsyncHttpClient()

        client["https://api.themoviedb.org/3/movie/top_rated?api_key=30d9e089c0af0cb09de7bf8cd9fed339", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {

                //getting movie poster
                val baseURL = "https://www.themoviedb.org/t/p/original"
                val movies = json.jsonObject.getJSONArray("results")
                val randomMovieIndex = (0 until movies.length()).random()
                val posterPath = movies.getJSONObject(randomMovieIndex).getString("poster_path")

                if (posterPath.isNotEmpty()) {
                    movieImageURL = baseURL + posterPath // construct the URL for the poster image
                } else {
                    // if the movie doesn't have a poster image, call the function again to get a new movie
                    getMovieImageURL()
                }

                //first method i tried and couldn't get working COME BACK LATER
                /*var JSONArray = json.jsonObject.getJSONArray("results")
                Log.d("Movie Image", "response successful")

                for (i in 0 until JSONArray.length()) {
                    posterPathArray.add(JSONArray.getJSONObject(i).getString("poster_path"))
                }
                for (i in 0 until posterPathArray.size) {
                    if (posterPathArray[i].isNotEmpty()) {
                        movieImageURL = baseURL+posterPathArray[i] // construct the URL for the poster image
                    } else {
                        // if the movie doesn't have a poster image, call the function again to get a new movie
                        getMovieImageURL()
                    }
                }
                 */

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