package com.graduation.design.bestellen.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Parcelable
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.graduation.design.bestellen.R

/**
 * Created by pan on 2017/5/4.
 * baseActivity without toolbar
 */

abstract class BaseActivity : AppCompatActivity() {
    protected var mShowTransparentStatus = true
    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        if (Build.VERSION.SDK_INT >= 21 && mShowTransparentStatus) {
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(getMenuRes(), menu)
        return true
    }

    fun getMenuRes() = R.menu.menu_scrolling

    fun snack(view: View?, text: String, duration: Int = Snackbar.LENGTH_SHORT) {
        if (view == null) {
            return
        }
        Snackbar.make(view, text, duration).show()
    }

    inline fun <reified T: Activity> Activity.navigate(vararg params:Pair<String, Parcelable>) {
        val intent = Intent(this, T::class.java)
        params.forEach { intent.putExtra(it.first, it.second) }
        startActivity(intent)
    }

    fun Context.dip2Px(dip: Float) : Float{
        return dip * resources.displayMetrics.density
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}