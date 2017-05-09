package com.graduation.design.bestellen.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by pan on 2017/5/3.
 * base fragment
 */
abstract class BaseFragment : Fragment() {
    var isFirstVisible = true

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        e("onCreateView")
        return inflater?.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        initViews()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleHint && isFirstVisible) {
            Handler().post {
                initData()
            }
            isFirstVisible = false
        }
    }

    abstract fun getLayout(): Int

    abstract fun initData(): Unit

    open fun initViews(): Unit {}

    fun Fragment.e(s: String) {
        Log.e((this::class.java.canonicalName).toString(), s)
    }

    inline fun <reified T: Activity> Fragment.startActivity(vararg params:Pair<String, Parcelable>) {
        val intent = Intent(activity, T::class.java)
        params.forEach { intent.putExtra(it.first, it.second) }
        startActivity(intent)
    }
}