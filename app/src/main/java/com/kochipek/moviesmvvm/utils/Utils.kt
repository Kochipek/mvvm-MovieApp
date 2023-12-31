package com.kochipek.moviesmvvm.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kochipek.moviesmvvm.R

class Utils {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "43cf09ac0f4b550009ffed8bc2834096"
    }
}


fun ImageView.downloadFromUrl(url: String? , progressDrawable: CircularProgressDrawable) {
    val loadOptions = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(this.context)
        .setDefaultRequestOptions(loadOptions)
        .load(url)
        .into(this)
}
fun placeholderProgressBar(context : Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply{
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}