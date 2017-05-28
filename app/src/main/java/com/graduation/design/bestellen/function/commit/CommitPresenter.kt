package com.graduation.design.bestellen.function.commit

import com.graduation.design.bestellen.model.ReservationData

/**
 * Created by pan on 2017/5/26.
 * Commit Presenter
 */
class CommitPresenter(view: CommitContract.View, data: CommitData) : CommitContract.Presenter {
    val mView = view
    val mData = data


    override fun start() {
    }

    override fun commitReservation(applyInfo: ReservationData) {
        mView.showProgressDialog(true)
        mData.getCommitResult(applyInfo, onSuccess = {
            mView.showProgressDialog(false)
            mView.finishActivity()
        }, onFailed = { s: String ->
            mView.showError(s)
            mView.showProgressDialog(false)
        })
    }
}