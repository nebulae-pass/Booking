package com.graduation.design.bestellen.function.room

import com.graduation.design.bestellen.common.Utils
import com.graduation.design.bestellen.model.DailyRoomOccupation
import com.graduation.design.bestellen.model.OccupyTime
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by pan on 2017/5/11.
 * room booking presenter
 */
class RoomBookingPresenter(view: RoomBookingContract.View, data: RoomOccupationData) : RoomBookingContract.Presenter {
    private val mData = data
    private val mView = view

    val mDateFormat = SimpleDateFormat("MM-dd", Locale.getDefault())
    override fun start() {
    }

    override fun loadRoomOccupationData(rid: String, date: String, period: Int) {
        mView.showLoadingDialog(true)
        mData.getRoomOccupation(rid, date, period.toString(), onSuccess = { it ->
            getFormDataBy(it, mView.getDataSet())
            mView.setRawData(it as ArrayList<DailyRoomOccupation>)
            mView.updateForm()
            mView.showLoadingDialog(false)
        }, onFailed = { it ->
            mView.showLoadingDialog(false)
            mView.showSnack(it)
        })
    }

    override fun commitReservation(rid: String, date: String, occupyTime: OccupyTime, onSuccess: () -> Unit, onFailed: (e: String) -> Unit) {

    }

    fun getFormDataBy(list: List<DailyRoomOccupation>, data: FormAdapter.FormData?) {
        if (data == null) {
            return
        }
        val row = data.row
        if (Utils.formatDate(list[0].date) == Utils.formatDate(Date())) {
            val time = System.currentTimeMillis()
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = time
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            var now = getTimeIndex(hour, minute)
            val end = list[0].openingTime.end
            if (now > end) {
                now = end
            }
            list[0].openingTime = OccupyTime(start = now, end = list[0].openingTime.end)
        }
        for (j in 0..6) {
            val oc = list[j]
            val occupation = oc.occupyList
            occupation.forEach {
                for (i in it.start..it.end) {
                    row[i].statusList[j] = 1
                }
            }
            for (i in 0..oc.openingTime.start - 1) {
                row[i].statusList[j] = -1
            }
            for (i in oc.openingTime.end..27) {
                row[i].statusList[j] = -1
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

    fun getTimeIndex(hour: Int, minute: Int, start: Int = 8): Int {
        if (hour + 1 < start) {
            return 0
        }
        return (hour - start + 1) * 2 + minute / 30
    }
}