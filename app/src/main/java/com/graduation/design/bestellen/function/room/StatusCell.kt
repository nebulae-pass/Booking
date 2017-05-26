package com.graduation.design.bestellen.function.room

import android.content.Context
import android.graphics.Canvas
import com.graduation.design.bestellen.R
import com.nebula.utils.DensityUtil
import com.nebula.wheel.FormCell

/**
 * Created by pan on 2017/5/12.
 *
 * show background with [Status]
 */
class StatusCell(context: Context) : FormCell(context) {
    val mAvailableBackground = R.color.room_available_color
    val mClosedBackground = R.color.room_closed_color
    val mOccupiedBackground = R.color.room_occupied_color
    val mSelectedBackground = R.color.room_selected_color

    var mStatus = Status.CLOSED

    enum class Status {
        CLOSED,
        OCCUPIED,
        SELECTED,
        AVAILABLE,
    }

    fun setStatus(status: Status) {
        val colorRes: Int
        mStatus = status
        when (status) {
            Status.CLOSED -> {
                colorRes = mClosedBackground
            }
            Status.AVAILABLE -> {
                colorRes = mAvailableBackground
            }
            Status.SELECTED -> {
                colorRes = mSelectedBackground
            }
            Status.OCCUPIED -> {
                colorRes = mOccupiedBackground
            }
            else -> {
                colorRes = mClosedBackground
            }
        }
        setBackgroundColor(colorRes)
    }

    fun getStatus():Status = mStatus

    override fun draw(canvas: Canvas?) {
    }

    override fun calculateCellWidth(remainColCount: Int, remainViewWidth: Int): Float {
        val default = DensityUtil.dip2Px(mContext, 30f)
        val max = remainViewWidth / remainColCount

        if (default > max) {
            return default
        }
        return max.toFloat()
    }

    override fun calculateCellHeight(remainRowCount: Int, remainViewHeight: Int): Float {
        val default = DensityUtil.dip2Px(mContext, 30f)
        val max = remainViewHeight / remainRowCount

        if (default > max) {
            return default
        }
        return max.toFloat()
    }
}