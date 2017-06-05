package com.graduation.design.bestellen.function.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.graduation.design.bestellen.R
import com.graduation.design.bestellen.base.BaseDialog
import com.graduation.design.bestellen.common.Account
import com.graduation.design.bestellen.common.CommonTextWatcher
import com.graduation.design.bestellen.data.LocalDataRepository
import com.graduation.design.bestellen.model.User
import kotlinx.android.synthetic.main.dialog_login.*


/**
 * Created by pan on 2017/5/19.
 * login dialog
 */
class LoginDialog : BaseDialog(), LoginContract.View {
    val mPresenter: LoginPresenter = LoginPresenter(this, LoginData())
    var mSuccessListener: (user: User) -> Unit = {}

    //from view
    override fun setPresenter(presenter: LoginContract.Presenter) {

    }

    override fun onFailed(message: String) {
        loginButton.errorText = message
        loginButton.progress = -1
    }

    override fun onSuccess(user: User) {
        Account.account = user.account
        Account.name = user.name
        val data = LocalDataRepository.getInstance()
        data.account = user.account
        data.name = user.name
        data.password = passwordEditText.text.toString()
        dismiss()
        mSuccessListener(user)
    }

    override fun startProgressing() {
        loginButton.progress = 50
    }

    override fun setLoginEnable() {
        loginButton.isEnabled = true
    }

    override fun setLoginDisable() {
        loginButton.isEnabled = false
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)
        isCancelable = false
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    fun setOnLoginSuccessListener(onSuccess: (user: User) -> Unit) {
        mSuccessListener = onSuccess
    }

    override fun initViews() {
        loginButton.isIndeterminateProgressMode = true
        mPresenter.start()
        titleFab.post {
            val height = titleFab.height
            (inputPanel.layoutParams as RelativeLayout.LayoutParams).topMargin = height / 2
        }
        close.setOnClickListener {
            dismiss()
        }
        accountEditText.addTextChangedListener(object : CommonTextWatcher(){
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mPresenter.checkInput(accountEditText.text.toString(), passwordEditText.text.toString())
            }
        })
        accountEditText.setOnFocusChangeListener { _, _ ->
            if (loginButton.progress == -1) {
                loginButton.progress = 0
            }
        }
        passwordEditText.addTextChangedListener(object : CommonTextWatcher(){
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mPresenter.checkInput(accountEditText.text.toString(), passwordEditText.text.toString())
            }
        })
        passwordEditText.setOnFocusChangeListener { _, _ ->
            if (loginButton.progress == -1) {
                loginButton.progress = 0
            }
        }

        loginButton.setOnClickListener {
            attemptLogin()
        }
    }

    fun attemptLogin() {
        if (loginButton.progress == -1) {
            loginButton.progress = 0
            return
        }
        if (loginButton.progress != 0) {
            return
        }
        mPresenter.login(accountEditText.text.toString(), passwordEditText.text.toString())
    }

    override fun getLayout() = R.layout.dialog_login
}