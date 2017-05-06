package com.graduation.design.bestellen.base

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.view.View
import butterknife.ButterKnife

/**
 * Created by pan on 2017/5/4.
 */

abstract class BaseActivity : AppCompatActivity() {
    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        ButterKnife.bind(this)
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

    open fun addToolbar(): Unit{}

    open fun initViews(): Unit {}

    open fun initListener(): Unit {}
}