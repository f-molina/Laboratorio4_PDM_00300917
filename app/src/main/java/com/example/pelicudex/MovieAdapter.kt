package com.example.pelicudex

import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MovieAdapter(var movies: List<Movie>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MovieAdapter.ViewHolder {
        var view = LayoutInflater.from(p0.context).inflate(R.layout.cardview_movie, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

    }

    override fun onBindViewHolder(p0: MovieAdapter.ViewHolder, p1: Int) {

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }
}