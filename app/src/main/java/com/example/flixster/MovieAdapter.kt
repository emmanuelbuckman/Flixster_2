package com.example.flixster

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter (private val context: Context, private val movies: MutableList<Movie>): RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val moviePoster = itemView.findViewById<ImageView>(R.id.moviePoster)
        private val movieTitle = itemView.findViewById<TextView>(R.id.movieTitle)
        private val movieOverview = itemView.findViewById<TextView>(R.id.movieOverview)

        fun bind(movie: Movie){
            movieTitle.text = movie.title
            movieOverview.text = movie.overview
            Glide.with(context).load(movie.imageUrl).into(moviePoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount() = movies.size

}