package com.careemtest.movies.screens.main

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.careemtest.movies.R
import com.careemtest.movies.base.BaseActivity
import com.careemtest.movies.databinding.ActivityMainBinding

class MainActivity : BaseActivity(), MainContract.View {

    lateinit var bi: ActivityMainBinding
    lateinit var presenter: MainPresenter
    lateinit var repository: MainRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main)

        repository = MainRepository(this)
        presenter = MainPresenter(this, repository)
        bi.view = this
        bi.presenter = presenter
        presenter.initScreen()
    }

    override fun setupViews() {

    }
}
