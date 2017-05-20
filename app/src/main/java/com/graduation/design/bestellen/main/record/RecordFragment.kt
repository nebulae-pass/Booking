package com.graduation.design.bestellen.main.record

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.graduation.design.bestellen.R
import com.graduation.design.bestellen.base.BaseFragment
import com.graduation.design.bestellen.common.Account
import com.graduation.design.bestellen.main.reservation.ReservationAdapter
import com.graduation.design.bestellen.model.RoomDetail
import com.graduation.design.bestellen.room.RoomBookingActivity
import kotlinx.android.synthetic.main.fragment_reservation.*

/**
 * Created by pan on 2017/5/5.
 * Record Fragment,show booking record
 */
class RecordFragment : BaseFragment() {
    var mAdapter:ReservationAdapter? = null
    override fun getLayout(): Int = R.layout.fragment_record

    override fun initData() {

    }

    override fun initViews() {
        mAdapter = ReservationAdapter(activity, ArrayList())
        mAdapter?.setOnItemClickListener { _, position ->
            val detail = mAdapter?.mDataSet?.get(position)
            startActivity<RoomBookingActivity>(Pair("detail", detail as RoomDetail))
        }
        recyclerView.setLayoutManager(LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false))
        recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL), 0)
        recyclerView.setAdapter(mAdapter)
    }

}