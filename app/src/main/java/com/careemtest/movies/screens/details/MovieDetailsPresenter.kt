package com.careemtest.movies.screens.details

import com.careemtest.movies.base.BasePresenter

/**
 * Created by wajahat.karim on 06/12/2017.
 */
class MovieDetailsPresenter(view: MovieDetailsContract.View, repository: MovieDetailsRepository) : BasePresenter<MovieDetailsContract.View>(view), MovieDetailsContract.Actions {
    val repository: MovieDetailsRepository = repository

    override fun initScreen() {
        _view?.setupViews()
    }

}
