package com.graduation.design.bestellen.main.reservation

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

    override fun initData() {
        mPresenter.loadData()
    }

    override fun setPresenter(presenter: ReservationContract.Presenter) {
    }

    override fun showLoading() {
        recyclerView.showLoadingView()
    }

    override fun hideLoading() {
        recyclerView.hideLoadingView()
    }

    override fun updateRecyclerView() {
        mAdapter?.notifyDataSetChanged()
    }

    override fun getDataSet(): MutableList<RoomDetail>? = mAdapter?.getDataSet()


    override fun getLayout(): Int = com.graduation.design.bestellen.R.layout.fragment_reservation

    override fun initViews() {
        mAdapter = ReservationAdapter(activity, ArrayList())
        recyclerView.setLayoutManager(LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false))
        recyclerView.setAdapter(mAdapter)
    }
}