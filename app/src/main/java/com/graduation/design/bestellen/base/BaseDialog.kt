package com.graduation.design.bestellen.base

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by pan on 2017/5/19.
 */
abstract class BaseDialog : DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        initViews()
        super.onViewCreated(view, savedInstanceState)
    }

    open fun initViews() {
    }

    abstract fun getLayout(): Int

    fun showDialog(manager: FragmentManager) {
        show(manager, "")
    }

    fun Fragment.dip2Px(dip: Float) : Float{
        return dip * resources.displayMetrics.density
    }
}
