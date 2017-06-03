package com.graduation.design.bestellen.function.login

import com.google.gson.Gson
import com.graduation.design.bestellen.common.Logs
import com.graduation.design.bestellen.data.RemoteDataRepository
import com.graduation.design.bestellen.model.RequestResult
import com.graduation.design.bestellen.model.LoginData
import com.graduation.design.bestellen.model.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by pan on 2017/5/19.
 * LoginData
 */
class LoginData : RemoteDataRepository() {
    fun loginForResult(account: String, password: String, onSuccess: (user: User) -> Unit,
                       onFailed: (message: String) -> Unit) {
        val service = mRetrofit.create(LoginService::class.java)
        service.login(LoginData(account, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({response->
                    val result = response.body()
                    if(result.code == 1){
                        onFailed(result.message)
                    }else{
                        onSuccess(Gson().fromJson(result.message, User::class.java))
                    }
                },{e->
                    onFailed("请求超时")
                    Logs.e(e.toString())
                })
    }

    interface LoginService {
        @POST("/login")
        fun login(@Body loginData: LoginData): Observable<Response<RequestResult>>

        @POST("/register")
        fun register(@Body loginData: LoginData): Observable<Response<RequestResult>>
    }
}