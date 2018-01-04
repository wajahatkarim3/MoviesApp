package com.careemtest.movies.dagger.components

import com.careemtest.movies.base.BaseActivity
import com.careemtest.movies.dagger.modules.AppModule
import com.careemtest.movies.dagger.modules.NetModule

import javax.inject.Singleton

import dagger.Component

/**
 * Created by wajahat.karim on 28/11/2017.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class))
interface NetComponent {
    fun inject(activity: BaseActivity)
}
