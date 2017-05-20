package com.graduation.design.bestellen.login

/**
 * Created by pan on 2017/5/19.
 * Login Presenter
 */
class LoginPresenter(view: LoginDialog, data: LoginData) : LoginContract.Presenter {

    val mView = view
    val mData = data

    override fun start() {
        mView.setLoginDisable()
    }

    override fun login(account: String, password: String) {
        mView.startProgressing()
        mData.loginForResult(account, password,
                onSuccess = {
                    mView.onSuccess()
                },
                onFailed = { it ->
                    mView.activity.runOnUiThread {
                        mView.onFailed(it)
                    }
                })
    }

    override fun checkInput(account: String, password: String) {
        if (account.length > 5 && password.length > 5) {
            mView.setLoginEnable()
        } else {
            mView.setLoginDisable()
        }
    }
}