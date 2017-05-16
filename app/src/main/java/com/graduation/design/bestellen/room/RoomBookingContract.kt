package com.graduation.design.bestellen.room

/**
 * Created by pan on 2017/5/11.
 */
interface RoomBookingContract {
    interface View{
        fun getDataSet()
    }
    interface Presenter{
        fun loadRoomOccupationData(date: String)
    }
}