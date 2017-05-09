package com.graduation.design.bestellen.main.reservation

import com.graduation.design.bestellen.base.BasePresenter
import com.graduation.design.bestellen.base.BaseView
import com.graduation.design.bestellen.model.RoomDetail

/**
 * Created by pan on 2017/5/6.
 * contract
 */
interface ReservationContract {
    interface View : BaseView<Presenter> {
        fun showLoading()

        fun hideLoading()

        fun getDataSet(): MutableList<RoomDetail>?

        fun updateRecyclerView()

    }

    interface Presenter: BasePresenter {
        fun initData()

        fun loadMoreData()
    }
}