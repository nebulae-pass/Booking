package com.graduation.design.bestellen.function.room

import com.graduation.design.bestellen.common.Logs
import com.graduation.design.bestellen.data.RemoteDataRepository
import com.graduation.design.bestellen.model.DailyRoomOccupation
import com.graduation.design.bestellen.model.RequestResult
import com.graduation.design.bestellen.model.ReservationData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by pan on 2017/5/20.
 * room occupationData
 */
class RoomOccupationData : RemoteDataRepository() {
    fun getRoomOccupation(rid: String, date: String, period: String, onSuccess: (List<DailyRoomOccupation>) -> Unit,
                          onFailed: (String) -> Unit) {
        val service = mRetrofit.create(RoomService::class.java)
        service.getRoomOccupation(rid, date, period)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    val result = response.body()
                    onSuccess(result)
                }, { e ->
                    e.printStackTrace()
                    onFailed(e.toString())
                    Logs.e(e.toString())
                })
    }



    interface RoomService {
        @GET("/room_occupied/{rid}/{date}/{period}")
        fun getRoomOccupation(@Path("rid") rid: String, @Path("date") date: String, @Path("period") period: String)
                : Observable<Response<List<DailyRoomOccupation>>>


    }
}