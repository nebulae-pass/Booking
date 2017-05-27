package com.graduation.design.bestellen.function.login

import com.graduation.design.bestellen.common.Account

/**
 * Created by pan on 2017/5/27.
 * Login Manager
 */
class LoginManager {
    companion object {
        fun checkLogin() = Account.account.isEmpty()
    }
}