package com.graduation.design.bestellen.room

import com.graduation.design.bestellen.common.Logs
import com.graduation.design.bestellen.data.RemoteDataRepository
import com.graduation.design.bestellen.model.DailyRoomOccupation
import com.graduation.design.bestellen.model.OccupyTime
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by pan on 2017/5/20.
 * room occupationData
 */
class RoomOccupationData : RemoteDataRepository() {
    fun getRoomOccupation(rid: String, onSuccess: (List<DailyRoomOccupation>) -> Unit,
                onFailed: (String) -> Unit) {
        val service = mRetrofit.create(RoomService::class.java)
        service.getRoomOccupation(rid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    val result = response.body()
                    result.forEach { Logs.v(it.toString()) }

                    onSuccess(result)
                }, { e ->
                    onFailed(e.toString())
                    Logs.e(e.toString())
                })
//        occupyList.add(OccupyTime(0, 3))
//        occupyList.add(OccupyTime(5, 10))
//
//
//        val dataList = ArrayList <DailyRoomOccupation>()
//        dataList.add(DailyRoomOccupation("a", "a", occupyList, OccupyTime(0, 20)))
//        dataList.add(DailyRoomOccupation("a", "a", occupyList, OccupyTime(0, 20)))
//        dataList.add(DailyRoomOccupation("a", "a", occupyList, OccupyTime(0, 20)))
//        dataList.add(DailyRoomOccupation("a", "a", occupyList, OccupyTime(0, 20)))
//        dataList.add(DailyRoomOccupation("a", "a", occupyList, OccupyTime(0, 20)))
//        dataList.add(DailyRoomOccupation("a", "a", occupyList, OccupyTime(0, 20)))
//        dataList.add(DailyRoomOccupation("a", "a", occupyList, OccupyTime(0, 20)))
    }

    interface RoomService {
        @GET("/room_occupied/{rid}")
        fun getRoomOccupation(@Path("rid") rid: String): Observable<Response<List<DailyRoomOccupation>>>

    }
}