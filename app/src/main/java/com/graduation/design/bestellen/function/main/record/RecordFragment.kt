package com.graduation.design.bestellen.function.main.record

import android.support.design.widget.Snackbar
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import com.graduation.design.bestellen.R
import com.graduation.design.bestellen.base.BaseFragment
import com.graduation.design.bestellen.common.Logs
import com.graduation.design.bestellen.function.login.LoginDialog
import com.graduation.design.bestellen.model.Record
import kotlinx.android.synthetic.main.fragment_record.*

/**
 * Created by pan on 2017/5/5.
 * Record Fragment,show booking record
 */
class RecordFragment : BaseFragment(), RecordContract.View {
    var mAdapter: RecordAdapter? = null
    val mPresenter = RecordPresenter(this, RecordData())

    val loginDialog = LoginDialog()

    override fun setPresenter(presenter: RecordContract.Presenter) {
    }

    override fun getDataSet(): ArrayList<Record>? {
        return mAdapter?.mData
    }

    override fun updateRecyclerView() {
        mAdapter?.notifyDataSetChanged()
    }

    override fun showTips(message: String) {
        Snackbar.make(view as View, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun showRecyclerViewErrorStatus() {
        recyclerView.setError()
    }

    override fun showLoading(isShow: Boolean) {
        recyclerView.setRefreshing(isShow)
    }

    override fun getLayout(): Int = R.layout.fragment_record

    override fun initData() {
        mPresenter.loadData()
    }

    override fun initViews() {
        mAdapter = RecordAdapter(activity, ArrayList())
        recyclerView.setLayoutManager(LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false))
        recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL), 0)
        recyclerView.setAdapter(mAdapter)

        recyclerView.setOnRefreshListener {
            mPresenter.refreshData()
        }
        loginDialog.setOnLoginSuccessListener {
            mPresenter.loadData()
        }
        val textView = recyclerView.findErrorViewById(R.id.tips) as TextView
        textView.movementMethod = LinkMovementMethod.getInstance()
        val string = getString(R.string.please_log_in)
        val spannableStringBuilder = SpannableStringBuilder(string)
        spannableStringBuilder.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View?) {
                Logs.e("asd")

                loginDialog.showDialog(activity.supportFragmentManager)
            }
        }, 0, string.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.append(spannableStringBuilder)

    }


}