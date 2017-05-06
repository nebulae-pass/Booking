package com.graduation.design.bestellen.base

/**
 * Created by pan on 2017/5/5.
 */
interface BaseView<in T> {
    fun setPresenter(presenter: T)
}