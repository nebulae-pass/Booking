package com.graduation.design.bestellen.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Parcelable
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import com.graduation.design.bestellen.R
import com.graduation.design.bestellen.common.Account
import com.graduation.design.bestellen.function.login.LoginDialog
import com.graduation.design.bestellen.function.setting.SettingActivity
import kotlinx.android.synthetic.main.activity_main.*

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

    open fun getMenuRes() = R.menu.menu_scrolling

    fun snack(view: View?, text: String, duration: Int = Snackbar.LENGTH_SHORT) {
        if (view == null) {
            return
        }
        Snackbar.make(view, text, duration).show()
    }

    inline fun <reified T : Activity> Activity.navigate(vararg params: Pair<String, Parcelable>) {
        val intent = Intent(this, T::class.java)
        params.forEach { intent.putExtra(it.first, it.second) }
        startActivity(intent)
    }

    fun Context.dip2Px(dip: Float): Float {
        return dip * resources.displayMetrics.density
    }

    fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        if (item?.itemId == R.id.user_menu) {
            showUserMenu()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showUserMenu() {
        val popupWindow = PopupWindow(this)
        val view = LayoutInflater.from(this).inflate(R.layout.menu_layout_user, findViewById(android.R.id.content) as ViewGroup, false)
        val account = view.findViewById(R.id.user_id) as TextView
        val name = view.findViewById(R.id.user_name) as TextView
        val setting = view.findViewById(R.id.setting)
        val login = view.findViewById(R.id.login)
        if (Account.account.isEmpty()) {
            account.visibility = View.GONE
            name.text = "未登录"
            login.setOnClickListener {
                LoginDialog().showDialog(supportFragmentManager)
                popupWindow.dismiss()
            }
        } else {
            account.text = Account.account
            name.text = Account.name
            setting.visibility = View.VISIBLE
            name.setCompoundDrawables(getNameDrawable(), null, null, null)

            login.visibility = View.GONE
            setting.setOnClickListener {
                navigate<SettingActivity>()
            }
        }
        popupWindow.contentView = view
        popupWindow.isOutsideTouchable = true
        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.END or Gravity.TOP, -10, supportActionBar!!.height)
    }

    private fun getNameDrawable(): Drawable {
        val drawable = ContextCompat.getDrawable(this, R.drawable.drawable_shape)
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.RGB_565)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        drawable.draw(canvas)
        val paint = Paint()
        paint.color = 0xffffff
        canvas.drawText(Account.name[0].toString(), 0f, 0f, paint)


//        return BitmapDrawable(resources, bitmap)
        return drawable
    }
}