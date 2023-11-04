package com.kochipek.moviesmvvm.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Movie(
    @ColumnInfo("title")
    @SerializedName("title")
    val title: String?,
    @ColumnInfo("overview")
    @SerializedName("overview")
    val overview: String?,
    @ColumnInfo("poster_path")
    @SerializedName("poster_path")
    val poster_path: String?,
    @ColumnInfo("vote_average")
    @SerializedName("vote_average")
    val vote_average: String?,
    @ColumnInfo("release_date")
    @SerializedName("release_date")
    val release_date: String?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0

}

data class MovieResponse(val results : List<Movie>)