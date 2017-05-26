package com.graduation.design.bestellen.model

import android.os.Parcel
import android.util.SparseArray
import paperparcel.PaperParcel
import paperparcel.PaperParcelable
import java.util.*

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
                               val date: Date,
                               val occupyList: ArrayList<OccupyTime>,
                               val openingTime: OccupyTime) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}

@Suppress("unused")
@PaperParcel
data class OccupyTime(val id: Int = 0,
                      val start: Int,
                      val end: Int):PaperParcelable{
    companion object{
        @JvmField val CREATOR = PaperParcelOccupyTime.CREATOR
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelOccupyTime.writeToParcel(this, dest, flags)
    }
}


data class ApplyInfo(val uid: String,
                     val rid: String,
                     val date: Date,
                     val occupyTime: OccupyTime,
                     val applyAppend: String)

data class User(val account: String,
                val password: String)

data class RequestResult(val code: Int,
                         val message: String)

data class Record(val id: Long,
                  val name: String,
                  val date: Date,
                  val period: OccupyTime,
                  val status: Int,
                  val message: String)

data class ReservationData(val rid: String,
                           val uid: String,
                           val applyMessage:String,
                           val date:String,
                           val occupyTime: OccupyTime)


class RoomDevice {
    companion object {
        val net = 0
        val projection = 1
        val microphone = 2
    }
}
