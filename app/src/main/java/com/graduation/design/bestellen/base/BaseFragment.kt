package com.graduation.design.bestellen.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by pan on 2017/5/3.
 * base fragment
 */
abstract class BaseFragment : Fragment() {
    var mView: View? = null
    var isFirstVisible = true

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mView != null) {
           return mView
        }
        mView = inflater?.inflate(getLayout(), container, false)
        initViews()
        return mView
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleHint && isFirstVisible) {
            initData()
        }
    }

    abstract fun getLayout(): Int

    abstract fun initData(): Unit

    open fun initViews(): Unit {}

}