package com.careemtest.movies.base

import android.annotation.TargetApi
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputLayout
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.careemtest.movies.BuildConfig
import com.careemtest.movies.MoviesApp
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.drawee.view.DraweeTransition

import de.mateware.snacky.Snacky
import retrofit2.Retrofit
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {

    var exitAnim = 0
    var enterAnim = 0

    @Inject
    lateinit var _retrofit : Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElemts()
        }
        app().netComponent.inject(this@BaseActivity)
        //setContentView(R.layout.activity_base)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        if (item.itemId == android.R.id.home) {
            val currentapiVersion = Build.VERSION.SDK_INT
            if (currentapiVersion >= Build.VERSION_CODES.LOLLIPOP) {
                supportFinishAfterTransition()
            } else {
                finish()
            }
            return true
        }
        return false
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun setSharedElemts() {

        val currentapiVersion = Build.VERSION.SDK_INT
        if (currentapiVersion >= Build.VERSION_CODES.LOLLIPOP) {
            //getWindow().setSharedElementEnterTransition(DraweeTransform.createTransitionSet());

            window.sharedElementEnterTransition = DraweeTransition.createTransitionSet(
                    ScalingUtils.ScaleType.FOCUS_CROP, ScalingUtils.ScaleType.FOCUS_CROP)
            window.sharedElementReturnTransition = DraweeTransition.createTransitionSet(
                    ScalingUtils.ScaleType.FOCUS_CROP, ScalingUtils.ScaleType.FOCUS_CROP)

        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (BuildConfig.DEBUG) {
            if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
                //Do something
                return super.onKeyDown(keyCode, event)
            } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
                //logoutApp()
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    fun BaseActivity.app() : MoviesApp
    {
        return application as MoviesApp
    }

    fun BaseActivity.snack(message: String) {
        val viewGroup = (findViewById<ViewGroup>(android.R.id.content) as ViewGroup).getChildAt(0) as ViewGroup
        var snack = Snacky.builder().setView(viewGroup).build()
        snack.setDuration(Snackbar.LENGTH_LONG)
        snack.setText(message)
        snack.view.setOnClickListener { snack.dismiss() }
        snack.show()
    }

    fun BaseActivity.error(message: String) {
        val viewGroup = (findViewById<ViewGroup>(android.R.id.content) as ViewGroup).getChildAt(0) as ViewGroup
        var snack = Snacky.builder().setView(viewGroup).error()
        snack.setDuration(Snackbar.LENGTH_INDEFINITE)
        snack.setText(message)
        snack.view.setOnClickListener { snack.dismiss() }
        snack.show()
    }

    fun BaseActivity.error(message: String, action: String, listener: (View) -> Unit) {
        val viewGroup = (findViewById<ViewGroup>(android.R.id.content) as ViewGroup).getChildAt(0) as ViewGroup
        var snack = Snacky.builder().setView(viewGroup).error()
        snack.setDuration(Snackbar.LENGTH_INDEFINITE)
        snack.setAction(action, listener)
        snack.setText(message)
        snack.view.setOnClickListener { snack.dismiss() }
        snack.show()
    }

    fun BaseActivity.success(message: String) {
        val viewGroup = (findViewById<ViewGroup>(android.R.id.content) as ViewGroup).getChildAt(0) as ViewGroup
        var snack = Snacky.builder().setView(viewGroup).success()
        snack.setDuration(Snackbar.LENGTH_INDEFINITE)
        snack.setText(message)
        snack.view.setOnClickListener { snack.dismiss() }
        snack.show()
    }

    fun BaseActivity.success(message: String, action: String, listener: (View) -> Unit) {
        val viewGroup = (findViewById<ViewGroup>(android.R.id.content) as ViewGroup).getChildAt(0) as ViewGroup
        var snack = Snacky.builder().setView(viewGroup).success()
        snack.setDuration(Snackbar.LENGTH_INDEFINITE)
        snack.setAction(action, listener)
        snack.setText(message)
        snack.view.setOnClickListener { snack.dismiss() }
        snack.show()
    }

    fun BaseActivity.withDelay(delay: Long = 700L, method: () -> Unit) {

        Handler().postDelayed({ method.invoke() }, delay)
    }

    fun TextInputLayout.text(): String
    {
        return this.editText?.text?.toString() ?: ""
    }

    fun View.enable()
    {
        this.isEnabled = true
    }

    fun View.disable()
    {
        this.isEnabled = false
    }

    fun View.gone()
    {
        this.visibility = View.GONE
    }

    fun View.visible()
    {
        this.visibility = View.VISIBLE
    }
}
