package com.graduation.design.bestellen.main

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import butterknife.bindView
import com.graduation.design.bestellen.R
import com.graduation.design.bestellen.base.BaseActivity
import com.graduation.design.bestellen.main.reservation.ReservationFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by pan on 2017/5/4.
 * main activity
 */
class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
}