package com.graduation.design.bestellen.base

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast

/**
 * Created by pan on 2017/5/4.
 * baseActivity without toolbar
 */

abstract class BaseActivity : AppCompatActivity() {
    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView: View = window.decorView
            val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            decorView.systemUiVisibility = option
            window.statusBarColor = Color.TRANSPARENT
        }
        addToolbar()
        initViews()
        initListener()
    }

    open fun addToolbar(): Unit {}

    open fun initViews(): Unit {}

    open fun initListener(): Unit {}

    fun Activity.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, text, duration).show()
    }

    fun Activity.snack(view: View, text: String, duration: Int = Snackbar.LENGTH_SHORT) {
        Snackbar.make(view, text, duration)
    }
}