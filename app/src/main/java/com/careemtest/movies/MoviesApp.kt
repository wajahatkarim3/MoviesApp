package com.careemtest.movies

import android.app.Application
import com.careemtest.movies.dagger.components.DaggerNetComponent
import com.careemtest.movies.dagger.components.NetComponent
import com.careemtest.movies.dagger.modules.AppModule
import com.careemtest.movies.dagger.modules.NetModule
import com.careemtest.movies.utils.AppConstants
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.stetho.Stetho

/**
 * Created by wajahat.karim on 04/01/2018.
 */
class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this@MoviesApp)
        Fresco.initialize(this);
    }

    val netComponent: NetComponent by lazy {
        DaggerNetComponent.builder()
                .appModule(AppModule(this@MoviesApp))
                .netModule(NetModule(AppConstants.HTTP.BASE_URL_API))
                .build()
    }
}