package com.careemtest.movies.base

import android.support.annotation.CallSuper

/**
 * Created by Wajahat Karim on 4/21/2017.
 */

abstract class BasePresenter<V> protected constructor(view: V) {

    protected var _view: V? = null

    /**
     * Check if the view is attached.
     * This checking is only necessary when returning from an asynchronous call
     */
    protected val isViewAttached: Boolean
        get() = _view != null

    init {
        attachView(view)
    }

    /**
     * Call this method in {Activity#onCreate}
     * or {Fragment#onAttach(Context)} to attach the MVP View object
     */
    @CallSuper
    fun attachView(view: V) {
        _view = view
    }

    /**
     * Call this method in {Activity#onDestroy()}
     * or {Fragment#onDetach()} to detach the MVP View object
     */
    @CallSuper
    fun detachView() {
        _view = null
    }
}