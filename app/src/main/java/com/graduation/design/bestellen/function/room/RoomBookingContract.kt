package com.graduation.design.bestellen.function.room

import com.graduation.design.bestellen.base.BasePresenter
import com.graduation.design.bestellen.model.DailyRoomOccupation
import com.graduation.design.bestellen.model.OccupyTime

/**
 * Created by pan on 2017/5/11.
 * room booing contract
 */
interface RoomBookingContract {
    interface View {
        fun getDataSet(): FormAdapter.FormData?
        fun updateForm()
        fun setRawData(rawData:ArrayList<DailyRoomOccupation>)
        fun showLoadingDialog(isShow:Boolean)
        fun showSnack(message:String)
    }

    interface Presenter : BasePresenter {
        fun loadRoomOccupationData(rid: String, date: String, period: Int = 7)
        fun commitReservation(rid: String, date: String, occupyTime: OccupyTime, onSuccess: () -> Unit, onFailed: (e: String) -> Unit)

    }
}