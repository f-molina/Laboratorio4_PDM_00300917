package com.example.pelicudex

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var movieList: ArrayList<Movie> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun initRecycleView(){
        viewManager = LinearLayoutManager(this)
        movieAdapter = MovieAdapter(movieList)
        movie_list_rv.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = movieAdapter
        }
    }

    fun addMovieToList(movie: Movie){
        movieList.add(movie)
        movieAdapter.changeList(movieList)
    }

    private inner class FetchMovie : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String): String {

            if(params.isNullOrEmpty()) return ""

            val movieName = params[0]
            val movieUrl = NetworkUtils.buildSearchUrl(movieName)

            return try {
                NetworkUtils.getResponseFromHttpUrl(movieUrl)

            } catch (e: IOException) {
                ""
            }
        }

        override fun onPostExecute(movieInfo: String) {
            super.onPostExecute(movieInfo)
            if(!movieInfo.isEmpty()){
                val movieJson = JSONObject(movieInfo)
                if(movieJson.getString("Response") == "True"){
                    val movie = Gson().fromJson<Movie>(movieInfo, Movie::class.java)
                    addMovieToList(movie)
                }else{
                    Snackbar.make(main_ll, "No existe pelicula en la base", Snackbar.LENGTH_SHORT).show()
                }
            }

        }

    }

}
