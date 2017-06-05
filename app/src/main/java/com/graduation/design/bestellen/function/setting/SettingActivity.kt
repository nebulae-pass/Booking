package com.graduation.design.bestellen.function.setting

import android.os.Bundle
import com.graduation.design.bestellen.R
import com.graduation.design.bestellen.base.BaseActivity
import com.graduation.design.bestellen.common.Account
import com.graduation.design.bestellen.data.LocalDataRepository
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
    }

    override fun initViews() {
        supportActionBar?.title = "账户设置"
    }

    override fun initListener() {
        logout.setOnClickListener {
            Account.account = ""
            Account.name = ""
            LocalDataRepository.getInstance().account = ""
            LocalDataRepository.getInstance().name = ""
            LocalDataRepository.getInstance().password = ""
            snack(topLayout, "已登出")
        }
    }
}
