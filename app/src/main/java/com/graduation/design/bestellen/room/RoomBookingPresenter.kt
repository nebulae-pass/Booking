package com.graduation.design.bestellen.room

import com.graduation.design.bestellen.model.DailyRoomOccupation
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by pan on 2017/5/11.
 * room booking presenter
 */
class RoomBookingPresenter(view: RoomBookingContract.View, data: RoomOccupationData) : RoomBookingContract.Presenter {
    val mData = data
    val mView = view

    val mDateFormat = SimpleDateFormat("MM-dd", Locale.getDefault())
    override fun start() {

    }

    override fun loadRoomOccupationData(rid: String, date: String, period: Int) {
        mData.getRoomOccupation(rid, date, period.toString(), onSuccess = { it ->
            getFormDataBy(it, mView.getDataSet())
        }, onFailed = { it ->

        })
    }

    fun getFormDataBy(list: List<DailyRoomOccupation>, data: FormAdapter.FormData?) {
        if (data == null) {
            return
        }
        val row = data.row
        for (j in 0..6) {
            val oc = list[j]
            val occupation = oc.occupyList
            for (i in 0..oc.openingTime.start - 1) {
                row[i].statusList[j] = -1
            }
            for (i in oc.openingTime.end..27) {
                row[i].statusList[j] = -1
            }
            occupation.forEach {
                for (i in it.start..it.end - 1) {
                    row[i].statusList[j] = 1
                }
            }
            for (i in 0..27) {
                row[i].period = getTime(i)
            }
        }
        data.title.clear()
        data.title.add("时间段")
        for (i in 0..list.size - 1) {
            data.title.add(mDateFormat.format(list[i].date))
        }
    }

    fun getTime(index: Int, start: Int = 8): String {
        val suffix: String
        if (index % 2 == 1) {
            suffix = ":30"
        } else {
            suffix = ":00"
        }
        return (index / 2 + start).toString() + suffix
    }
}