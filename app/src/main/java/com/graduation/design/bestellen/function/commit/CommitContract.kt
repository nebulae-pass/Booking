package com.graduation.design.bestellen.function.commit

import com.graduation.design.bestellen.base.BasePresenter
import com.graduation.design.bestellen.base.BaseView
import com.graduation.design.bestellen.model.ReservationData

/**
 * Created by pan on 2017/5/26.
 * CommitContract
 */
interface CommitContract {
    interface View : BaseView<Presenter> {
        fun showProgressDialog(isShown: Boolean)
        fun finishActivity()
        fun showError(string: String)
    }

    interface Presenter : BasePresenter {
        fun commitReservation(applyInfo: ReservationData)
    }

}