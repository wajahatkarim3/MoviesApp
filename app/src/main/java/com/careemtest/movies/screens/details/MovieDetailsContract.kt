package com.careemtest.movies.screens.details

/**
 * Created by wajahat.karim on 06/12/2017.
 */
interface MovieDetailsContract {
    interface View {
        fun setupViews()
    }

    interface Actions {
        fun initScreen()
    }

    interface Repository {

    }
}