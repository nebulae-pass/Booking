package com.graduation.design.bestellen.function.main.record

import com.graduation.design.bestellen.common.Account
import com.graduation.design.bestellen.model.OccupyTime
import com.graduation.design.bestellen.model.Record
import java.util.*

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
            val response = ArrayList<Record>()
            response.add(Record("1", "逸夫楼303", Date(), OccupyTime(start = 3, end = 7), 2, "审核成功，请会议结束后按时归还会议室"))
            response.add(Record("1", "计算机楼B303", Date(), OccupyTime(start = 6, end = 10), 1, "审核中，请耐心等待"))
            response.add(Record("1", "逸夫楼303", Date(), OccupyTime(start = 6, end = 10), 3, "信誉度太低，申请失败"))
            response.add(Record("1", "逸夫楼303", Date(), OccupyTime(start = 6, end = 10), 4, "使用已完成"))

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
        mData.getRecord(Account.account, onSuccess = { response ->
            mView.getDataSet()?.clear()
            mView.getDataSet()?.addAll(response)
            mView.updateRecyclerView()
            mView.showLoading(false)
        }, onFailed = { string ->
            mView.showTips(string)
            mView.showLoading(false)
        })
    }
}