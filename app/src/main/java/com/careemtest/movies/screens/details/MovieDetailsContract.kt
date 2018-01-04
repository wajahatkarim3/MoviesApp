package com.careemtest.movies.screens.details

import com.careemtest.movies.models.ActorModel

/**
 * Created by wajahat.karim on 06/12/2017.
 */
interface MovieDetailsContract {
    interface View {
        fun setupViews()
        fun showActors(list:ArrayList<ActorModel>)
    }

    interface Actions {
        fun initScreen()
        fun loadActorsList()
    }

    interface Repository {
        interface OnActorsLoadedResponse
        {
            fun onSuccess(list: ArrayList<ActorModel>)
            fun onError(message: String)
        }
        fun loadActors(movieID:String, callback: OnActorsLoadedResponse?)
    }
}