package com.graduation.design.bestellen.main.reservation

import com.graduation.design.bestellen.model.RoomDetail

/**
 * Created by pan on 2017/5/6.
 * data
 */
class ReservationData {
    fun loadRoomDetailData(): ArrayList<RoomDetail> {
        val roomDetailList = ArrayList<RoomDetail>()
        roomDetailList.add(RoomDetail(1, "逸夫301", "教室", 150, "南区逸夫教学楼", listOf(true, true, true), ""))
        roomDetailList.add(RoomDetail(1, "逸夫301", "教室", 150, "南区逸夫教学楼", listOf(true, true, true), ""))
        roomDetailList.add(RoomDetail(1, "逸夫301", "教室", 150, "南区逸夫教学楼", listOf(true, true, true), ""))
        roomDetailList.add(RoomDetail(1, "逸夫301", "教室", 150, "南区逸夫教学楼", listOf(true, false, true), ""))
        roomDetailList.add(RoomDetail(1, "逸夫303", "教室", 100, "南区逸夫教学楼", listOf(true, true, true), ""))
        roomDetailList.add(RoomDetail(1, "逸夫301", "教室", 150, "南区逸夫教学楼", listOf(false, true, true), ""))
        roomDetailList.add(RoomDetail(1, "逸夫301", "教室", 150, "南区逸夫教学楼", listOf(true, true, true), ""))
        roomDetailList.add(RoomDetail(1, "逸夫301", "教室", 150, "南区逸夫教学楼", listOf(true, true, true), ""))
        return roomDetailList
    }
}