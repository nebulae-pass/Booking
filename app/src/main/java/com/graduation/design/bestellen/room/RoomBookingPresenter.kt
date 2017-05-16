package com.graduation.design.bestellen.room

/**
 * Created by pan on 2017/5/11.
 * room booking presenter
 */
class RoomBookingPresenter(view: RoomBookingContract.View): RoomBookingContract.Presenter {
    val mView = view

    override fun loadRoomOccupationData(date: String) {
    }
}