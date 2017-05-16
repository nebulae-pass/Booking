package com.graduation.design.bestellen.model

import android.os.Parcel
import android.util.SparseArray
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Created by pan on 2017/5/4.
 * all data module, constant value
 */
@Suppress("unused")
@PaperParcel
data class RoomDetail(val rid: String,
                      val name: String,
                      val type: String,
                      val capacity: Int,
                      val location: String,
                      val belong: String,
                      val deviceEnable: List<Boolean>,
                      val append: String) : PaperParcelable {
    companion object {
        @JvmField val CREATOR = PaperParcelRoomDetail.CREATOR
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelRoomDetail.writeToParcel(this, dest, flags)
    }
}

data class DailyRoomOccupation(val rid: String,
                               val date: String,
                               val occupyList: ArrayList<OccupyTime>,
                               val openingTime: OccupyTime) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}

data class WeeklyRoomOccupation(val rid: String,
                                val weeklyOccupy: SparseArray<DailyRoomOccupation>)


data class OccupyTime(val start: Int,
                      val end: Int)

data class ApplyInfo(val uid: String,
                     val rid: String,
                     val occupyTime: OccupyTime,
                     val applyAppend: String)


class RoomDevice {
    companion object {
        val net = 0
        val projection = 1
        val microphone = 2
    }
}
