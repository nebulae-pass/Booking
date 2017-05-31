package com.graduation.design.bestellen.function.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.graduation.design.bestellen.R
import com.graduation.design.bestellen.base.BaseToolbarActivity
import com.graduation.design.bestellen.common.Logs
import com.graduation.design.bestellen.function.main.reservation.ReservationAdapter
import com.graduation.design.bestellen.function.main.reservation.ReservationContract
import com.graduation.design.bestellen.function.main.reservation.ReservationData
import com.graduation.design.bestellen.function.main.reservation.ReservationPresenter
import com.graduation.design.bestellen.model.ConditionSearchData
import com.graduation.design.bestellen.model.RoomDetail
import kotlinx.android.synthetic.main.activity_search_result.*

class SearchResultActivity : BaseToolbarActivity(), ReservationContract.View {
    var mAdapter:ReservationAdapter? = null
    var mPresenter = ReservationPresenter(this, ReservationData())

    companion object{
        fun start(context: Context, data: ConditionSearchData) {
            val intent = Intent(context, SearchResultActivity::class.java)
            intent.putExtra("data", data)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)
    }

    override fun initViews() {
        supportActionBar?.title = "搜索结果"
        val data = intent.getParcelableExtra<ConditionSearchData>("data")
        mAdapter = ReservationAdapter(this, ArrayList())
        recyclerView.setAdapter(mAdapter)
    }

    override fun setPresenter(presenter: ReservationContract.Presenter) {

    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun getDataSet(): MutableList<RoomDetail>? {
        return mAdapter?.mDataSet
    }

    override fun updateRecyclerView() {
        mAdapter?.notifyDataSetChanged()
    }

    override fun showTips(resId: Int, duration: Int) {
    }
}
