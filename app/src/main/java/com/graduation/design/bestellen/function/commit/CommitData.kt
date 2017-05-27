package com.graduation.design.bestellen.function.commit

import com.graduation.design.bestellen.common.Logs
import com.graduation.design.bestellen.data.RemoteDataRepository
import com.graduation.design.bestellen.model.ApplyInfo
import com.graduation.design.bestellen.model.RequestResult
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by pan on 2017/5/27.
 * Remote Data
 */
class CommitData : RemoteDataRepository() {
    fun getCommitResult(data: ApplyInfo, onSuccess: () -> Unit, onFailed: (String) -> Unit) {
        val service = mRetrofit.create(CommitService::class.java)
        service.commitReservation(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    val result = response.body()
                    if (result.code == 0) {
                        onSuccess()
                    } else {
                        onFailed(result.message)
                    }
                }, { e ->
                    onFailed(e.toString())
                    Logs.e(e.toString())
                })
    }

    interface CommitService {
        @POST()
        fun commitReservation(@Body data: ApplyInfo): Observable<Response<RequestResult>>
    }
}