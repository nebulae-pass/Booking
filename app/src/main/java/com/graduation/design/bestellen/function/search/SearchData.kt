package com.graduation.design.bestellen.function.search

import com.graduation.design.bestellen.data.RemoteDataRepository
import com.graduation.design.bestellen.model.ConditionSearchData
import com.graduation.design.bestellen.model.RoomDetail
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by pan on 2017/6/1.
 * Data
 */
class SearchData :RemoteDataRepository(){
    fun loadResultData(data: ConditionSearchData, onSuccess: (result: ArrayList<RoomDetail>) -> Unit, onFailed: (msg: String) -> Unit) {
        val service = mRetrofit.create(SearchResultService::class.java)
        service.getResult(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ response->
                    onSuccess(response.body())
                },{e->
                    onFailed(e.toString())
                })
    }

    interface SearchResultService{
        @POST("/search")
        fun getResult(@Body data:ConditionSearchData): Observable<Response<ArrayList<RoomDetail>>>
    }
}