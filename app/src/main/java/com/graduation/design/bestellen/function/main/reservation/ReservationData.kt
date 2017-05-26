package com.graduation.design.bestellen.function.main.reservation

import com.graduation.design.bestellen.common.Logs
import com.graduation.design.bestellen.data.RemoteDataRepository
import com.graduation.design.bestellen.model.RoomDetail
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by pan on 2017/5/6.
 * data
 */
class ReservationData : RemoteDataRepository() {
    fun loadData(onResponse: (dataList: List<RoomDetail>) -> Unit
                 , onError: (e: Throwable) -> Unit): Unit {
        val service = mRetrofit.create(GetRoomDetail::class.java)

        service.getRoomDetail()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    onResponse(response.body())
                }, { e ->
                    onError(e)
                    Logs.e(e.toString())
                })
    }

    interface GetRoomDetail {
        @GET("/rooms")
        fun getRoomDetail(): Observable<Response<List<RoomDetail>>>
    }
}