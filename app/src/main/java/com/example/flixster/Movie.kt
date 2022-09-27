package com.example.flixster

import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import org.json.JSONArray

@Parcelize
data class Movie (
    val movieId: Int,
    val posterPath: String,
    val title: String,
    val overview: String,
): Parcelable { @IgnoredOnParcel
    val imageUrl = "https://image.tmdb.org/t/p/w500/$posterPath"

    companion object{
        fun fromJsonArray(movieJSONArray: JSONArray) : List<Movie> {
            val movies = mutableListOf<Movie>()
            for (i in 0 until movieJSONArray.length()){
                val mJson = movieJSONArray.getJSONObject(i)
                movies.add(
                    Movie(
                        mJson.getInt("id"),
                        mJson.getString("poster_path"),
                        mJson.getString("title"),
                        mJson.getString("overview")
                    )
                )
            }
            return movies
        }
    }
}