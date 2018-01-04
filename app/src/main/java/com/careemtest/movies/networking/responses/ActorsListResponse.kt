package com.careemtest.movies.networking.responses

import com.careemtest.movies.models.ActorModel
import com.careemtest.movies.models.MovieModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by wajah on 1/5/2018.
 */
class ActorsListResponse : BaseResponse() {

    @SerializedName("cast") @Expose var actorsList: ArrayList<ActorModel>? = null
    @SerializedName("id") @Expose var page: Int = 1

}