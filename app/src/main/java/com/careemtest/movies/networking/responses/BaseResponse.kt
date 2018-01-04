package com.careemtest.movies.networking.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by wajahat.karim on 04/01/2018.
 */
open class BaseResponse {

    @SerializedName("status_message") @Expose var statusMessage: String = ""
    @SerializedName("success") @Expose var success: Boolean = true
    @SerializedName("status_code") @Expose var statusCode: Int = 200
}