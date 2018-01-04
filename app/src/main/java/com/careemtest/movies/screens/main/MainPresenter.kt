package com.careemtest.movies.screens.main

import com.careemtest.movies.base.BasePresenter
import com.careemtest.movies.models.MovieModel
import com.careemtest.movies.networking.callbacks.CustomCallback
import com.careemtest.movies.networking.responses.MoviesListResponse
import com.careemtest.movies.networking.services.ApiService
import com.careemtest.movies.utils.AppConstants
import retrofit2.Call

/**
 * Created by wajahat.karim on 06/12/2017.
 */
class MainPresenter(view: MainContract.View, repository: MainRepository) : BasePresenter<MainContract.View>(view), MainContract.Actions {

    val repository: MainRepository = repository

    override fun initScreen() {
        _view?.setupViews()
        loadLatestMovies(1, "")
    }

    fun loadLatestMovies(page: Int,releaseDate:String)
    {
        if (page == 1)
            _view?.showMainLoading()

        repository?.getLatestMovies(page, releaseDate, object : MainContract.Repository.OnLatestMoviesResponse
        {
            override fun onSuccess(list: List<MovieModel>, pagination:Boolean) {
                _view?.showContent(list, pagination)
            }

            override fun onError(message: String) {
                _view?.showError(message)
            }
        })
    }

}
