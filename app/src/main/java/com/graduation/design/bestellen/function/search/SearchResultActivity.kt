package com.graduation.design.bestellen.function.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.graduation.design.bestellen.R
import com.graduation.design.bestellen.base.BaseToolbarActivity
import com.graduation.design.bestellen.function.main.reservation.ReservationAdapter
import com.graduation.design.bestellen.function.room.RoomBookingActivity
import com.graduation.design.bestellen.model.ConditionSearchData
import com.graduation.design.bestellen.model.RoomDetail
import kotlinx.android.synthetic.main.activity_search_result.*
import kotlin.collections.ArrayList

class SearchResultActivity : BaseToolbarActivity(), SearchContract.View {
    lateinit var mAdapter: ReservationAdapter
    var mPresenter = SearchPresenter(this, SearchData())

    companion object {
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val data = intent.getParcelableExtra<ConditionSearchData>("data")
        mAdapter = ReservationAdapter(this, ArrayList())
        recyclerView.setAdapter(mAdapter)
        recyclerView.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL), 0)
        mPresenter.loadData(data)
    }

    override fun initListener() {
        mAdapter.setOnItemClickListener { _, position ->
            val detail = mAdapter.mDataSet[position]
            navigate<RoomBookingActivity>(Pair("detail", detail))
        }
    }

    override fun setPresenter(presenter: SearchContract.Presenter) {

    }

    override fun disableRefresh() {
        recyclerView.refreshLayout?.isEnabled = false
    }

    override fun showLoading(isShow: Boolean) {
        recyclerView.setRefreshing(isShow)
    }

    override fun getDataSet(): MutableList<RoomDetail> {
        return mAdapter.mDataSet
    }

    override fun updateRecyclerView() {
        mAdapter.notifyDataSetChanged()
    }

    override fun showTips(resId: Int, duration: Int) {
    }
}
