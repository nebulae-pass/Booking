package com.graduation.design.bestellen.function.main.record

import android.content.Context
import android.widget.TextView
import com.graduation.design.bestellen.R
import com.graduation.design.bestellen.base.BaseViewHolder
import com.graduation.design.bestellen.common.Utils
import com.graduation.design.bestellen.model.OccupyTime
import com.graduation.design.bestellen.model.Record
import com.graduation.design.bestellen.view.BaseRecyclerAdapter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by pan on 2017/5/21.
 * Record Adapter
 */
class RecordAdapter(context: Context, data: ArrayList<Record>) : BaseRecyclerAdapter(context) {

    val mContext = context
    val mData = data
    val mFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())


    override fun setViews(viewHolder: BaseViewHolder?, position: Int) {
        val data = mData[position]
        viewHolder?.setText(R.id.nameText, data.name)
                ?.setText(R.id.dateText, mFormat.format(data.date))
                ?.setText(R.id.messageText, data.message)
                ?.setText(R.id.periodText, mContext.getString(R.string.booking_period, getPeriodString(data.period)))
        setViewStatus(viewHolder?.obtainView(R.id.statusText) as TextView, data.status)
    }

    private fun getPeriodString(occupyTime: OccupyTime): String {
        return Utils.getTime(occupyTime.start) + "-" + Utils.getTime(occupyTime.end)
    }

    /**
     * 1:Under review
     * 2:success
     * 3:failed
     */
    private fun setViewStatus(view: TextView, status: Int) {
        val strId: Int
        val colorId: Int
        when (status) {
            1 -> {
                strId = R.string.under_review
                colorId = R.color.review_under_color
            }
            2 -> {
                strId = R.string.review_success
                colorId = R.color.review_success_color
            }
            3 -> {
                strId = R.string.review_failed
                colorId = R.color.review_failed_color
            }
            else -> {
                strId = R.string.under_review
                colorId = R.color.review_under_color
            }
        }
        view.setText(strId)
        view.setBackgroundResource(colorId)
    }


    override fun getItemCount() = mData.size

    override fun getItemViewType(position: Int) = R.layout.item_record

}