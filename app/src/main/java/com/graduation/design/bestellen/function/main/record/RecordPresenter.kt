package com.graduation.design.bestellen.function.main.record

import com.graduation.design.bestellen.common.Account

/**
 * Created by pan on 2017/5/21.
 * RecordPresenter
 */
class RecordPresenter(view: RecordContract.View, recordData: RecordData) : RecordContract.Presenter {
    val mView = view
    val mData = recordData

    override fun start() {
    }

    override fun loadData() {
        if (Account.account.isEmpty()) {
            mView.showRecyclerViewErrorStatus()
            return
        }
        mView.showLoading(true)
        mData.getRecord(Account.account, onSuccess = { response ->
            mView.getDataSet()?.addAll(response)
            mView.updateRecyclerView()
            mView.showLoading(false)
        }, onFailed = { string ->
            mView.showTips(string)
            mView.showLoading(false)
        })
    }

    override fun refreshData() {
        if (Account.account.isEmpty()) {
            mView.showTips("请登录")
            mView.showLoading(false)
            return
        }
        mData.getRecord(Account.account, onSuccess = {response ->
            mView.getDataSet()?.clear()
            mView.getDataSet()?.addAll(response)
            mView.updateRecyclerView()
            mView.showLoading(false)
        },onFailed = {string ->
            mView.showTips(string)
            mView.showLoading(false)
        })
    }
}