package com.careemtest.movies.dagger.modules

import com.careemtest.movies.MoviesApp
import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Created by wajahat.karim on 28/11/2017.
 */

@Module
class AppModule(internal var mApplication: MoviesApp) {

    @Provides
    @Singleton
    internal fun provideApp(): MoviesApp {
        return mApplication
    }

}
