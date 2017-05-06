package com.graduation.design.bestellen.model

import android.os.Parcel
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Created by pan on 2017/5/4.
 * all data module, constant value
 */
@Suppress("unused")
@PaperParcel
data class RoomDetail(var id: Int,
                      var name: String,
                      var type: String,
                      var capacity: Int,
                      var location: String,
                      var deviceEnable: List<Boolean>,
                      var append: String) : PaperParcelable {
    companion object {
        @JvmField val CREATOR = PaperParcelRoomDetail.CREATOR
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelRoomDetail.writeToParcel(this, dest, flags)
    }
}

data class RoomOccupation(var id: Int,
                          var date: String,
                          var occupyList: Array<OccupyTime>) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}

data class OccupyTime(var start: String,
                      var end: String)

data class ApplyInfo(var start: String,
                     var end: String,
                     var applyAppend: String)

class RoomDevice {
    companion object {
        val net = 1
        val projection = 2
        val microphone = 3
    }
}
