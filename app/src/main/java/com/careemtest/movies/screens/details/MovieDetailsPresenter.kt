package com.careemtest.movies.screens.details

import com.careemtest.movies.base.BasePresenter
import com.careemtest.movies.models.ActorModel
import com.careemtest.movies.models.MovieModel

/**
 * Created by wajahat.karim on 06/12/2017.
 */
class MovieDetailsPresenter(view: MovieDetailsContract.View, repository: MovieDetailsRepository) : BasePresenter<MovieDetailsContract.View>(view), MovieDetailsContract.Actions {
    val repository: MovieDetailsRepository = repository

    var movieModel: MovieModel? = null

    override fun initScreen() {
        _view?.setupViews()
        loadActorsList()
    }

    override fun loadActorsList() {
        repository?.loadActors(movieModel?.id.toString(), object : MovieDetailsContract.Repository.OnActorsLoadedResponse
        {
            override fun onSuccess(list: ArrayList<ActorModel>) {
                _view?.showActors(list)
            }

            override fun onError(message: String) {
                // Nothing to do
            }
        })

    }

}
