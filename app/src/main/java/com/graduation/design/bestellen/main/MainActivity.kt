package com.graduation.design.bestellen.main

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import butterknife.bindView
import com.graduation.design.bestellen.R
import com.graduation.design.bestellen.base.BaseActivity
import com.graduation.design.bestellen.model.ApplyInfo

/**
 * Created by pan on 2017/5/4.
 * main activity
 */
class MainActivity : BaseActivity() {

    val mViewPager: ViewPager by bindView(R.id.container)
    val mTabLayout: TabLayout by bindView(R.id.tab_layout)
    val mToolbar: Toolbar by bindView(R.id.toolbar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun initViews() {
        setSupportActionBar(mToolbar)
        supportActionBar?.title = "主页"

        val fragments = ArrayList<Fragment>()
        fragments.add(ReservationFragment())
        fragments.add(RecordFragment())

        val titles = ArrayList<String>()
        titles.add("会议室列表")
        titles.add("预约记录")

        mViewPager.adapter = ContainerPagerAdapter(supportFragmentManager, fragments, titles)
        mViewPager.currentItem = 1
        mTabLayout.setupWithViewPager(mViewPager)
    }
}