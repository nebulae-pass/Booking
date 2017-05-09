package com.graduation.design.bestellen.roomdetail

import android.os.Bundle
import android.support.design.widget.Snackbar
import com.graduation.design.bestellen.R
import com.graduation.design.bestellen.base.BaseActivity
import kotlinx.android.synthetic.main.activity_scrolling.*

class RoomDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
    }

    override fun initViews() {
        setSupportActionBar(toolbar)
        super.initViews()
    }

    override fun initListener() {
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }
}
