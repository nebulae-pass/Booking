package com.graduation.design.bestellen.function.commit

import com.graduation.design.bestellen.model.ApplyInfo

/**
 * Created by pan on 2017/5/26.
 * Commit Presenter
 */
class CommitPresenter(view: CommitContract.View, data: CommitData) : CommitContract.Presenter {
    val mView = view
    val mData = data


    override fun start() {
    }

    override fun commitReservation(applyInfo: ApplyInfo) {
        mData.getCommitResult(applyInfo, onSuccess = {

        }, onFailed = { s: String ->

        })
    }
}