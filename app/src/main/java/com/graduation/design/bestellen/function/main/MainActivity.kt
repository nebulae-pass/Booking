package com.graduation.design.bestellen.function.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.graduation.design.bestellen.R
import com.graduation.design.bestellen.base.BaseActivity
import com.graduation.design.bestellen.common.Account
import com.graduation.design.bestellen.common.Logs
import com.graduation.design.bestellen.function.login.LoginDialog
import com.graduation.design.bestellen.function.main.record.RecordFragment
import com.graduation.design.bestellen.function.main.reservation.ReservationFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by pan on 2017/5/4.
 * main activity
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mShowTransparentStatus = false
        setContentView(R.layout.activity_main)
    }

    override fun initViews() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "主页"

        val fragments = ArrayList<Fragment>()
        fragments.add(ReservationFragment())
        fragments.add(RecordFragment())

        val titles = ArrayList<String>()
        titles.add("会议室列表")
        titles.add("预约记录")

        container.adapter = ContainerPagerAdapter(supportFragmentManager, fragments, titles)
        tabLayout.setupWithViewPager(container)
    }

    override fun initListener() {
        floatingButton.setOnClickListener {
            LoginDialog().showDialog(supportFragmentManager)
        }
    }
}