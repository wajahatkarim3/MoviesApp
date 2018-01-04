package com.careemtest.movies.networking.services

import com.careemtest.movies.networking.responses.MoviesListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by wajahat.karim on 04/01/2018.
 */
interface ApiService {

    @GET("movie/now_playing")
    fun getNowPlaying(
            @Query("api_key") apiKey: String,
            @Query("page") page: Int
    ) : Call<MoviesListResponse>

}