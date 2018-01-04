package com.careemtest.movies.screens.details

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.careemtest.movies.R
import com.careemtest.movies.base.BaseActivity
import com.careemtest.movies.databinding.ActivityMovieDetailsBinding
import com.careemtest.movies.databinding.ActorItemLayoutBinding
import com.careemtest.movies.models.ActorModel
import com.thetechnocafe.gurleensethi.liteutils.RecyclerAdapterUtil

class MovieDetailsActivity : BaseActivity(), MovieDetailsContract.View {

    lateinit var bi: ActivityMovieDetailsBinding
    lateinit var repository: MovieDetailsRepository
    lateinit var presenter: MovieDetailsPresenter

    var actorsAdapter: RecyclerAdapterUtil<ActorModel>? = null
    var actorsList = ArrayList<ActorModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)

        if (intent.hasExtra("movieModel"))
        {
            repository = MovieDetailsRepository(this)
            presenter = MovieDetailsPresenter(this, repository)
            presenter.retrofit = _retrofit
            bi.view = this
            bi.presenter = presenter

            presenter.movieModel = intent.getParcelableExtra("movieModel")
            bi.movieModel = presenter.movieModel

            presenter.initScreen()
        }
        else{
            finish()
        }

    }

    override fun setupViews() {
        // Toolbar
        setSupportActionBar(bi.toolbar)

        // RecyclerView
        actorsAdapter = RecyclerAdapterUtil(this, actorsList, R.layout.actor_item_layout)
        actorsAdapter?.addOnDataBindListener { itemView, item, position, innerViews ->
            var bbb = DataBindingUtil.bind<ActorItemLayoutBinding>(itemView)
            bbb.actorModel = item
            bbb.executePendingBindings()
        }
        bi.recyclerCast.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        bi.recyclerCast.adapter = actorsAdapter
    }

    override fun showActors(list: ArrayList<ActorModel>) {
        actorsList.clear()
        actorsList.addAll(list)
        actorsAdapter?.notifyDataSetChanged()
    }
}
