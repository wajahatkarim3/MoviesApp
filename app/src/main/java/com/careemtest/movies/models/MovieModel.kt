package com.careemtest.movies.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import com.facebook.drawee.view.SimpleDraweeView
import android.databinding.BindingAdapter



/**
 * Created by wajahat.karim on 04/01/2018.
 */
@Parcelize
data class MovieModel (

        @SerializedName("vote_count") @Expose var voteCount: Int,
        @SerializedName("vote_average") @Expose var voteAverage: Float,
        @SerializedName("id") @Expose var id: Int,
        @SerializedName("title") @Expose var title: String,
        @SerializedName("poster_path") @Expose var posterPath: String,
        @SerializedName("overview") @Expose var overview: String,
        @SerializedName("release_date") @Expose var release_date: String

) : Parcelable

@BindingAdapter("posterPath")
fun loadPosterUrl(imageView: SimpleDraweeView, iUrl: String) {
    imageView.setImageURI(iUrl)
}