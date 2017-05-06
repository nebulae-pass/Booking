package com.graduation.design.bestellen.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder

/**
 * Created by pan on 2017/5/3.
 * base fragment
 */
abstract class BaseFragment : Fragment() {
    var mView: View? = null
    var unbind: Unbinder? = null
    var isFirstVisible = true

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mView != null) {
           return mView
        }
        mView = inflater?.inflate(getLayout(), container, false)
        if (mView != null) unbind = ButterKnife.bind(this, mView as View)
        initViews()
        return mView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbind?.unbind()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleHint && isFirstVisible) {
            //todo: load data
        }
    }

    abstract fun getLayout(): Int

    open fun initViews(): Unit {}

}