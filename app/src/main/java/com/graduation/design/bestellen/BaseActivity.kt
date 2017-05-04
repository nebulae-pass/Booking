package com.graduation.design.bestellen

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import butterknife.ButterKnife


/**
 * Created by pan on 2017/5/2.
 * base activity
 */
abstract class BaseActivity : AppCompatActivity() {
    var mToolbar: Toolbar? = null

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        ButterKnife.bind(this)
        val content = findViewById(android.R.id.content) as FrameLayout
        mToolbar = LayoutInflater.from(this).inflate(R.layout.view_toolbar, content, false) as Toolbar
        val child = content.getChildAt(0) as ViewGroup
        child.addView(mToolbar, 0)
        setSupportActionBar(mToolbar)
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView: View = window.decorView
            val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            decorView.systemUiVisibility = option
            window.statusBarColor = Color.TRANSPARENT
        }
        initViews()
        initListener()
    }

    private fun createStatusView(): View {
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        val statusBarHeight = resources.getDimensionPixelSize(resourceId)

        val statusView = View(this)
        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                statusBarHeight)
        statusView.layoutParams = params
        statusView.setBackgroundColor(R.color.colorPrimary)
        return statusView
    }

    open fun initViews(): Unit {}

    open fun initListener(): Unit {}

    fun setTitle(title: String) {
        supportActionBar?.title = title
    }
}


