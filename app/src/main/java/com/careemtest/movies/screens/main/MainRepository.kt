package com.careemtest.movies.screens.main

import android.content.Context
import com.careemtest.movies.base.BaseActivity
import com.careemtest.movies.base.BaseRepository
import com.careemtest.movies.networking.responses.MoviesListResponse
import com.careemtest.movies.networking.services.ApiService
import com.careemtest.movies.utils.AppConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection.HTTP_OK


/**
 * Created by wajahat.karim on 07/12/2017.
 */
class MainRepository(context: BaseActivity) : BaseRepository(context), MainContract.Repository {

    override fun getLatestMovies(page: Int, releaseDate:String, callback: MainContract.Repository.OnLatestMoviesResponse) {
        context._retrofit.create(ApiService::class.java)
                .getNowPlaying(AppConstants.HTTP.API_KEY, page, releaseDate)
                .enqueue(object : Callback<MoviesListResponse>{

                    override fun onResponse(call: Call<MoviesListResponse>?, response: Response<MoviesListResponse>?) {

                        if (response?.code() == HTTP_OK)
                        {
                            callback?.onSuccess(response?.body()?.results ?: emptyList(), page < response?.body()?.totalPages ?: 1)
                        }
                        else
                        {
                            callback?.onError(response?.body()?.statusMessage ?: "Unknown Error Occured")
                        }
                    }

                    override fun onFailure(call: Call<MoviesListResponse>?, t: Throwable?) {
                        t?.printStackTrace()
                        callback?.onError(t?.localizedMessage ?: "Unknown Error Occured!")
                    }
                })
    }

}
