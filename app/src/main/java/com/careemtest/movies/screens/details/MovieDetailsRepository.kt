package com.careemtest.movies.screens.details

import android.content.Context
import com.careemtest.movies.base.BaseActivity
import com.careemtest.movies.base.BaseRepository
import com.careemtest.movies.networking.responses.ActorsListResponse
import com.careemtest.movies.networking.services.ApiService
import com.careemtest.movies.utils.AppConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection


/**
 * Created by wajahat.karim on 07/12/2017.
 */
class MovieDetailsRepository(context: BaseActivity) : BaseRepository(context), MovieDetailsContract.Repository {

    override fun loadActors(movieID: String, callback: MovieDetailsContract.Repository.OnActorsLoadedResponse?) {

        context._retrofit.create(ApiService::class.java)
                .getActorsList(movieID, AppConstants.HTTP.API_KEY)
                .enqueue(object : Callback<ActorsListResponse> {
                    override fun onFailure(call: Call<ActorsListResponse>?, t: Throwable?) {
                        t?.printStackTrace()
                        callback?.onError(t?.localizedMessage ?: "Unknown Error Occured!")
                    }

                    override fun onResponse(call: Call<ActorsListResponse>?, response: Response<ActorsListResponse>?) {
                        if (response?.code() == HttpURLConnection.HTTP_OK)
                        {
                            var list = response?.body()?.actorsList ?: arrayListOf()
                            if (list.size <= 10)
                                callback?.onSuccess(list)
                            else
                                callback?.onSuccess(ArrayList(list.subList(0, 9)))
                        }
                        else
                        {
                            callback?.onError(response?.body()?.statusMessage ?: "Unknown Error Occured")
                        }
                    }

                })

    }

}
