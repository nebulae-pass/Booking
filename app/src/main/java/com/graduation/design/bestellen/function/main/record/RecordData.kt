package com.graduation.design.bestellen.function.main.record

import com.graduation.design.bestellen.data.RemoteDataRepository
import com.graduation.design.bestellen.model.Record
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by pan on 2017/5/21.
 * RecordData
 */
class RecordData : RemoteDataRepository() {
    fun getRecord(uid: String, onSuccess: (response: ArrayList<Record>) -> Unit, onFailed: (string: String) -> Unit) {
        val service = mRetrofit.create(RecordService::class.java)
        service.getRecord(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    onSuccess(response.body())
                }, { e ->
                    onFailed(e.toString())
                })

    }

    interface RecordService {
        @GET("/records/{uid}")
        fun getRecord(@Path("uid") uid: String): Observable<Response<ArrayList<Record>>>
    }
}