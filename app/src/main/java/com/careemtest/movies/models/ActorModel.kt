package com.careemtest.movies.models

import android.databinding.BindingAdapter
import android.os.Parcelable
import com.facebook.drawee.view.SimpleDraweeView
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by wajah on 1/5/2018.
 */
data class ActorModel (

        @SerializedName("id") @Expose var id: Int,
        @SerializedName("credit_id") @Expose var credit_id: String,
        @SerializedName("name") @Expose var name: String,
        @SerializedName("character") @Expose var character: String,
        @SerializedName("profile_path") @Expose var profile: String

)