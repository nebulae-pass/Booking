package com.graduation.design.bestellen.function.search

import com.graduation.design.bestellen.base.BasePresenter
import com.graduation.design.bestellen.base.BaseView
import com.graduation.design.bestellen.model.ConditionSearchData
import com.graduation.design.bestellen.model.RoomDetail

/**
 * Created by pan on 2017/6/1.
 * contract
 */
interface SearchContract {
    interface View:BaseView<Presenter>{
        fun showLoading(isShow: Boolean)
        fun updateRecyclerView()
        fun getDataSet():MutableList<RoomDetail>
        fun showTips(resId:Int,duration:Int)
        fun disableRefresh()
    }

    interface Presenter:BasePresenter{
        fun loadData(data: ConditionSearchData)

    }
}