package com.graduation.design.bestellen.room

import com.graduation.design.bestellen.model.DailyRoomOccupation

/**
 * Created by pan on 2017/5/11.
 * room booking presenter
 */
class RoomBookingPresenter(view: RoomBookingContract.View, data: RoomOccupationData) : RoomBookingContract.Presenter {
    val mData = data
    val mView = view

    override fun start() {

    }

    override fun loadRoomOccupationData(rid: String, date: String, period: Int) {
        mData.getRoomOccupation(rid, onSuccess = { it ->
            getFormDataBy(it, mView.getDataSet())
        }, onFailed = { it ->

        })
    }

    fun getFormDataBy(list: List<DailyRoomOccupation>, data: Array<RoomBookingActivity.FormRow>?) {
        if (data == null) {
            return
        }
        for (j in 0..6) {
            val oc = list[j]
            val occupation = oc.occupyList
            for (i in 0..oc.openingTime.start) {
                data[i].statusList[j] = -1
            }
            for (i in oc.openingTime.end..27) {
                data[i].statusList[j] = -1
            }
            occupation.forEach {
                for (i in it.start..it.end - 1) {
                    data[i].statusList[j] = 1
                }
            }
            for (i in 0..27) {
                data[i].period = getTime(i)
            }
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