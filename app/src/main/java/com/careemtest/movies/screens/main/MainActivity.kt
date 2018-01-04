package com.careemtest.movies.screens.main

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.careemtest.movies.R
import com.careemtest.movies.base.BaseActivity
import com.careemtest.movies.databinding.ActivityMainBinding
import com.careemtest.movies.models.MovieModel
import com.thetechnocafe.gurleensethi.liteutils.RecyclerAdapterUtil
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Button
import com.kennyc.view.MultiStateView
import com.careemtest.movies.R.id.multistate





class MainActivity : BaseActivity(), MainContract.View {

    lateinit var bi: ActivityMainBinding
    lateinit var presenter: MainPresenter
    lateinit var repository: MainRepository

    var moviesAdapter: RecyclerAdapterUtil<MovieModel>? = null
    var moviesList = ArrayList<MovieModel>()

    var page = 1
    var nextPage = true
    var isLoading = false
    var snackBar: Snackbar? = null
    var layoutManager: GridLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main)

        repository = MainRepository(this)
        presenter = MainPresenter(this, repository)
        presenter.retrofit = _retrofit
        bi.view = this
        bi.presenter = presenter
        presenter.initScreen()

        //initDummy()
    }

    override fun setupViews() {

        // Toolbar
        setSupportActionBar(bi.toolbar)

        // RecyclerView
        moviesAdapter = RecyclerAdapterUtil(this, moviesList, R.layout.movie_item_layout)
        layoutManager = GridLayoutManager(this, 2)
        bi.listRecyclerView.setHasFixedSize(true)
        bi.listRecyclerView.layoutManager = layoutManager
        bi.listRecyclerView.adapter = moviesAdapter

        // Multistate
        bi.multistate.getView(MultiStateView.VIEW_STATE_ERROR)?.
                findViewById<Button>(R.id.retry)?.setOnClickListener {  }
    }

    override fun showMainLoading()
    {
        bi.multistate.viewState = MultiStateView.VIEW_STATE_LOADING
    }

    override fun showContent(list: List<MovieModel>, pagination: Boolean)
    {
        snackBar?.dismiss()
        moviesList.addAll(list)
        moviesAdapter?.notifyDataSetChanged()
        isLoading = false
        nextPage = pagination
        bi.multistate.viewState = MultiStateView.VIEW_STATE_CONTENT
    }

    override fun showError(message: String)
    {
        bi.multistate.viewState = MultiStateView.VIEW_STATE_ERROR
        error(message)
    }

    override fun showPageLoading()
    {

    }

    override fun showEmpty() {
        bi.multistate.viewState = MultiStateView.VIEW_STATE_EMPTY
    }

    fun initDummy()
    {
        moviesList.add(MovieModel())
        moviesList.add(MovieModel())
        moviesList.add(MovieModel())
        moviesList.add(MovieModel())
        moviesList.add(MovieModel())
        moviesList.add(MovieModel())
        moviesList.add(MovieModel())
        moviesList.add(MovieModel())
        moviesList.add(MovieModel())
        moviesList.add(MovieModel())
        moviesList.add(MovieModel())

        moviesAdapter?.notifyDataSetChanged()
    }
}
