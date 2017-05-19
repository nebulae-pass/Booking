package com.graduation.design.bestellen.login

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.RelativeLayout
import com.graduation.design.bestellen.R
import com.graduation.design.bestellen.base.BaseDialog
import com.graduation.design.bestellen.common.LoadingDialog
import kotlinx.android.synthetic.main.dialog_login.*


/**
 * Created by pan on 2017/5/19.
 * login dialog
 */
class LoginDialog : BaseDialog() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = Dialog(activity)
        dialog.setCanceledOnTouchOutside(true)
        dialog.show()

        val window = dialog.window
        window.setGravity(Gravity.CENTER)

        val lp = window.attributes
        val metrics = resources.displayMetrics

        lp.width = (metrics.widthPixels - dip2Px(16f)).toInt()
        window.attributes = lp
        return dialog
    }

    override fun initViews() {
        loginFab.post {
            val height = loginFab.height
            (inputPanel.layoutParams as RelativeLayout.LayoutParams).rightMargin = height / 2
            (inputPanel.layoutParams as RelativeLayout.LayoutParams).leftMargin = height / 2
            val marginDistance = tipText.height + accountLayout.height + passwordLayout.height - height
            (loginFab.layoutParams as RelativeLayout.LayoutParams).topMargin = marginDistance
        }
        loginFab.setOnClickListener {
            LoadingDialog().showDialog(activity.supportFragmentManager)
        }
    }

    override fun getLayout() = R.layout.dialog_login
}