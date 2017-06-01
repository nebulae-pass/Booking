package com.graduation.design.bestellen.function.search

import com.graduation.design.bestellen.model.ConditionSearchData

/**
 * Created by pan on 2017/6/1.
 * presenter
 */
class SearchPresenter (view:SearchContract.View, data: SearchData): SearchContract.Presenter {
    val mView = view
    val mData = data
    override fun start() {

    }

    override fun loadData(data:ConditionSearchData) {
        mView.showLoading(true)
        mData.loadResultData(data,onSuccess = {result ->
            mView.getDataSet().clear()
            mView.getDataSet().addAll(result)
            mView.updateRecyclerView()
            mView.showLoading(false)
            mView.disableRefresh()
        },onFailed = {
            mView.showLoading(false)
//            mView.showTips()
        })
    }
}