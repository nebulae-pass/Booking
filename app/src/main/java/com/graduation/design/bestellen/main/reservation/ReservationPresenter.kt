package com.graduation.design.bestellen.main.reservation

import android.util.Log

/**
 * Created by pan on 2017/5/6.
 * reservation presenter
 */
class ReservationPresenter(view: ReservationContract.View, data: ReservationData) : ReservationContract.Presenter {
    val mView: ReservationContract.View = view
    val mData: ReservationData = data

    init {
        mView.setPresenter(this)
    }

    override fun start() {
    }

    override fun loadData() {
        val dataList = mData.loadRoomDetailData()
        Log.e("asd", "load")
        mView.getDataSet()?.addAll(dataList)
        mView.updateRecyclerView()
    }


}
