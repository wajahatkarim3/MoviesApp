package com.careemtest.movies.screens.main

import android.content.Intent
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
import android.support.v4.app.ActivityOptionsCompat
import android.view.View
import android.widget.Button
import com.kennyc.view.MultiStateView
import com.careemtest.movies.R.id.multistate
import android.support.v7.widget.RecyclerView
import com.careemtest.movies.databinding.MovieItemLayoutBinding
import com.careemtest.movies.screens.details.MovieDetailsActivity
import com.careemtest.movies.utils.SnackUtils


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
    }

    override fun setupViews() {

        // Toolbar
        setSupportActionBar(bi.toolbar)

        // RecyclerView
        moviesAdapter = RecyclerAdapterUtil(this, moviesList, R.layout.movie_item_layout)
        moviesAdapter?.addOnDataBindListener { itemView, item, position, innerViews ->
            var bbb = DataBindingUtil.bind<MovieItemLayoutBinding>(itemView)
            bbb.movieModel = item
            bbb.executePendingBindings()
            //bbb.imgMoviePoster.setImageURI(item.posterPath)

            bbb.rootItemLayout.setOnClickListener { view ->

                var currentapiVersion = android.os.Build.VERSION.SDK_INT;
                if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP)
                {
                    var intent = Intent(this@MainActivity, MovieDetailsActivity::class.java)
                    intent.putExtra("movieModel", item)
                    var options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity, bbb.imgMoviePoster, "movie_image_transition")
                    startActivity(intent, options.toBundle())
                }
                else
                {
                    var intent = Intent(this@MainActivity, MovieDetailsActivity::class.java)
                    intent.putExtra("movieModel", item)
                    startActivity(intent)
                }

            }

        }
        layoutManager = GridLayoutManager(this, 2)
        bi.listRecyclerView.setHasFixedSize(true)
        bi.listRecyclerView.layoutManager = layoutManager
        bi.listRecyclerView.adapter = moviesAdapter

        // Pagination
        bi.listRecyclerView.addOnScrollListener(recyclerViewOnScrollListener)

        // Multistate
        bi.multistate.getView(MultiStateView.VIEW_STATE_ERROR)?.
                findViewById<View>(R.id.retry)?.setOnClickListener {  }
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

    private val recyclerViewOnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
        }

        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            if (!isLoading && nextPage) {

                var adapPos = layoutManager?.findLastVisibleItemPosition()
                var itemPos = moviesAdapter?.itemCount ?: 0
                itemPos = itemPos - 1

                if (adapPos == itemPos ) {
                    snackBar = SnackUtils.showLoadingSnackbar(this@MainActivity, "Loading more items...")
                    isLoading = true
                    page++
                    presenter.loadLatestMovies(page)
                }
            }
        }
    }
}
