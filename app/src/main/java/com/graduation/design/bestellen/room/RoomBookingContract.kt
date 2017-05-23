package com.graduation.design.bestellen.room

import com.graduation.design.bestellen.base.BasePresenter

/**
 * Created by pan on 2017/5/11.
 * room booing contract
 */
interface RoomBookingContract {
    interface View {
        fun getDataSet():FormAdapter.FormData?
        fun updateForm()
    }

    interface Presenter:BasePresenter {
        fun loadRoomOccupationData(rid: String, date: String, period: Int = 7)
    }
}