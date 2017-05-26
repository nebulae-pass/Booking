package com.graduation.design.bestellen.function.main.reservation

import com.graduation.design.bestellen.R

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

    override fun initData() {
        mView.showLoading()
        mData.loadData(onResponse = { dataList ->
            mView.getDataSet()?.addAll(dataList)
            mView.updateRecyclerView()
            mView.hideLoading()
        }, onError = {
            mView.hideLoading()
            mView.showSnackBar(R.string.request_time_out)
        })
    }

    override fun refreshData() {
        mView.showLoading()
        mData.loadData(onResponse =  { dataList ->
            mView.getDataSet()?.clear()
            mView.getDataSet()?.addAll(dataList)
            mView.updateRecyclerView()
            mView.hideLoading()
        },onError = {
            mView.hideLoading()
            mView.showSnackBar(R.string.refresh_failed_please_check)
        })
    }

    override fun loadMoreData() {
        mView.hideLoading()
    }
}
