package com.graduation.design.bestellen.function.main.record

import com.graduation.design.bestellen.base.BasePresenter
import com.graduation.design.bestellen.base.BaseView
import com.graduation.design.bestellen.model.Record

/**
 * Created by pan on 2017/5/21.
 * Record Contract
 */
interface RecordContract {
    interface View : BaseView<Presenter> {
        fun getDataSet(): MutableList<Record>?
        fun updateRecyclerView()

    }

    interface Presenter : BasePresenter {
        fun loadData()
    }
}