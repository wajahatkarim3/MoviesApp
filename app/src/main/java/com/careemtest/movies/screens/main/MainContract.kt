package com.careemtest.movies.screens.main

import com.careemtest.movies.models.MovieModel

/**
 * Created by wajahat.karim on 06/12/2017.
 */
interface MainContract {
    interface View {
        fun setupViews()
        fun showMainLoading()
        fun showContent(list: List<MovieModel>, pagination: Boolean)
        fun showError(message: String)
        fun showPageLoading()
        fun showEmpty()
    }

    interface Actions {
        fun initScreen()
    }

    interface Repository {
        interface OnLatestMoviesResponse
        {
            fun onSuccess(list:List<MovieModel>, pagination:Boolean)
            fun onError(message:String)
        }
        fun getLatestMovies(page:Int, callback:OnLatestMoviesResponse)
    }
}