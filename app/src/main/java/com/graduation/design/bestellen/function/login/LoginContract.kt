package com.graduation.design.bestellen.function.login

import com.graduation.design.bestellen.base.BasePresenter
import com.graduation.design.bestellen.base.BaseView

/**
 * Created by pan on 2017/5/19.
 * Login Contract
 */
interface LoginContract {
    interface View : BaseView<Presenter> {
        fun onFailed(message:String)
        fun onSuccess()
        fun startProgressing()
        fun setLoginEnable()
        fun setLoginDisable()
    }

    interface Presenter : BasePresenter {
        fun login(account: String, password: String)
        fun checkInput(account: String, password: String)

    }
}