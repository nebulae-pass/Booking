package com.graduation.design.bestellen

import android.app.Application
import com.graduation.design.bestellen.common.Account
import com.graduation.design.bestellen.data.LocalDataRepository
import com.graduation.design.bestellen.function.login.LoginData

/**
 * Created by pan on 2017/5/27.
 * Application
 */
class BestellenApp : Application() {
    override fun onCreate() {
        super.onCreate()
        LocalDataRepository.init(this)
        val data = LocalDataRepository.getInstance()
        if (!data.account.isEmpty()) {
            LoginData().loginForResult(data.account, data.password,onSuccess = { user ->
                Account.account = user.account
                Account.name = user.name
            },onFailed = {})


        }
    }
}