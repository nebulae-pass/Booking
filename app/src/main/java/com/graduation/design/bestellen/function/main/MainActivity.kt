package com.graduation.design.bestellen.function.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import com.graduation.design.bestellen.R
import com.graduation.design.bestellen.base.BaseActivity
import com.graduation.design.bestellen.function.main.record.RecordFragment
import com.graduation.design.bestellen.function.main.reservation.ReservationFragment
import com.graduation.design.bestellen.function.search.ConditionSearchDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

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
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    0)
        }

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
        var y: Float = 0f
        container.post {
            y = floatingButton.y
        }
        container.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (position == 0 && y != 0f) {
                    floatingButton.y = y + positionOffsetPixels
                }
            }
        })
//        val visible = View.GONE
//        tabLayout.visibility = visible
//        container.visibility = visible
//        floatingButton.post{
//            floatingButton.hide()
//        }
    }

    override fun initListener() {
        floatingButton.setOnClickListener {
            ConditionSearchDialog().showDialog(supportFragmentManager)
        }
    }
}