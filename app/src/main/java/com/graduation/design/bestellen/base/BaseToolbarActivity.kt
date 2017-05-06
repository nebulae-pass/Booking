package com.graduation.design.bestellen.base

import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import com.graduation.design.bestellen.R


/**
 * Created by pan on 2017/5/2.
 * base activity
 * func:
 * add toolbar
 * transparent status bar
 * butterknife bind
 */
abstract class BaseToolbarActivity : BaseActivity() {
    var mToolbar: Toolbar? = null

    override fun addToolbar() {
        val content = findViewById(android.R.id.content) as FrameLayout
        mToolbar = LayoutInflater.from(this).inflate(R.layout.view_toolbar, content, false) as Toolbar
        val child = content.getChildAt(0) as ViewGroup
        child.addView(mToolbar, 0)
        setSupportActionBar(mToolbar)
        Log.e("asd", "support")
    }

    fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }
}


