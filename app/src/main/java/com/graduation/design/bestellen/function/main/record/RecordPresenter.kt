package com.graduation.design.bestellen.function.main.record

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
        val list = ArrayList<Record>()
        list.add(Record(1,"逸夫楼303", Date(), OccupyTime(start = 3,end = 7),2,"审核成功，请会议结束后按时归还会议室"))
        list.add(Record(1, "逸夫楼303", Date(), OccupyTime(start = 6, end = 10), 1, "审核中，请耐心等待"))
        list.add(Record(1, "逸夫楼303", Date(), OccupyTime(start = 6, end = 10), 3, "信誉度太低，申请失败"))

        mView.getDataSet()?.addAll(list)
        mView.updateRecyclerView()
    }
}