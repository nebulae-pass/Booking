package com.graduation.design.bestellen.main.reservation

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.graduation.design.bestellen.base.BaseFragment
import com.graduation.design.bestellen.model.RoomDetail
import kotlinx.android.synthetic.main.fragment_reservation.*

/**
 * Created by pan on 2017/5/4.
 * reservationFragment, show all rooms at first(17/5/6)
 */
class ReservationFragment : BaseFragment(), ReservationContract.View {

    var mAdapter: ReservationAdapter? = null

    val mPresenter: ReservationPresenter = ReservationPresenter(this, ReservationData())

    //from view interface
    override fun setPresenter(presenter: ReservationContract.Presenter) {
    }

    override fun showLoading() {
        recyclerView.showLoadingView()
    }

    override fun hideLoading() {
        recyclerView.hideLoadingView()
    }

    override fun updateRecyclerView() {
        e("notify called")
        mAdapter?.notifyDataSetChanged()
    }

    override fun getDataSet(): MutableList<RoomDetail>? = mAdapter?.getDataSet()

    //from baseFragment
    override fun initData() {
        e("init")
        mPresenter.loadData()
    }

    override fun getLayout(): Int = com.graduation.design.bestellen.R.layout.fragment_reservation

    override fun initViews() {
        e("initView")
        mAdapter = ReservationAdapter(activity, ArrayList())
        recyclerView.setLayoutManager(LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false))
        recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL), 0)
        recyclerView.setAdapter(mAdapter)
    }
}