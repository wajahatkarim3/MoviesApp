package com.careemtest.movies.screens.main

import com.careemtest.movies.base.BasePresenter

/**
 * Created by wajahat.karim on 06/12/2017.
 */
class MainPresenter(view: MainContract.View, repository: MainRepository) : BasePresenter<MainContract.View>(view), MainContract.Actions {
    val repository: MainRepository = repository

    override fun initScreen() {
        _view?.setupViews()
    }

}
