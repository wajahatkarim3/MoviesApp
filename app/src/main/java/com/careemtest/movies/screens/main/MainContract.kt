package com.careemtest.movies.screens.main

/**
 * Created by wajahat.karim on 06/12/2017.
 */
interface MainContract {
    interface View {
        fun setupViews()
    }

    interface Actions {
        fun initScreen()
    }

    interface Repository {

    }
}