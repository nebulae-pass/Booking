package com.graduation.design.bestellen.function.room

import android.content.Context
import android.text.TextUtils
import com.graduation.design.bestellen.common.Utils
import com.graduation.design.bestellen.model.DailyRoomOccupation
import com.graduation.design.bestellen.model.OccupyTime
import com.nebula.utils.DensityUtil
import com.nebula.wheel.FormCell
import com.nebula.wheel.FormView
import com.nebula.wheel.StringFormCell

/**
 * Created by pan on 2017/5/11.
 * form adapter
 */
class FormAdapter(context: Context, data: FormData) : FormView.BaseAdapter<FormCell>() {
    val mData = data
    val mContext = context
    val mDataList = data.row
    var mRawData = ArrayList<DailyRoomOccupation>()

    val mTitle = data.title

    var mSelectedColumn = -1
    var mStart = -1
    var mEnd = -1

    init {
        mTitle.add("时间段")
        for (i in 0..6) {
            mTitle.add("")
        }
    }

    override fun bindCell(cell: FormCell, rowNumber: Int, colNumber: Int) {
        cell.isClickable = false
        if (cell is StringFormCell) {
            if (rowNumber == 0) {
                cell.content = mTitle[colNumber]
                return
            } else {
                cell.content = mDataList[rowNumber - 1].period
                return
            }
        }
        if (cell is StatusCell) {
            val status = getStatus(rowNumber, colNumber)
            if (status == StatusCell.Status.SELECTED || status == StatusCell.Status.AVAILABLE) {
                cell.isClickable = true
            }
            cell.setStatus(status)
            cell.setOnCellClickListener {
                if (cell.getStatus() == StatusCell.Status.AVAILABLE) {
                    if (mSelectedColumn == -1 || mSelectedColumn == colNumber) {
                        mSelectedColumn = colNumber
                        if (mStart == -1) {
                            mStart = rowNumber - 1
                        }
                        if (mStart > rowNumber - 1) {
                            mStart = rowNumber - 1
                        } else {
                            mEnd = rowNumber - 1
                        }
                        if (periodAlreadyOccupied(mStart, mEnd, colNumber)) {
                            mStart = mEnd
                        }
                        for (i in mStart..mEnd) {
                            mDataList[i].statusList[colNumber - 1] = 2
                        }
                    } else {
                        for (i in mStart..mEnd) {
                            mDataList[i].statusList[mSelectedColumn - 1] = 0
                        }
                        mSelectedColumn = colNumber
                        mStart = rowNumber - 1
                        mEnd = mStart
                        mDataList[mStart].statusList[mSelectedColumn - 1] = 2
                    }

                } else {
                    if (mStart == rowNumber - 1) {//first cell
                        mDataList[mStart].statusList[colNumber - 1] = 0
                        mStart += 1
                        if (mStart > mEnd) {
                            mStart = -1
                            mEnd = mStart
                        }
                    } else {
                        for (i in rowNumber - 1..mEnd) {
                            mDataList[i].statusList[colNumber - 1] = 0
                        }
                        mEnd = rowNumber - 2
                    }
                }
                notifyDataSetChanged()
            }
        }
    }

    private fun periodAlreadyOccupied(start: Int, end: Int, colNumber: Int): Boolean {
        return (start..end).any { mDataList[it].statusList[colNumber - 1] == 1 }
    }

    fun isSelectedCompleted() = mSelectedColumn != -1

    fun getSelectedDate() = Utils.formatDateByCHN(mRawData[mSelectedColumn - 1].date)

    fun getSelectedOccupyTime() = OccupyTime(start = mStart, end = mEnd)

    override fun getRowCount() = mDataList.size + 1

    override fun createCell(rowNumber: Int, colNumber: Int): FormCell {
        if (rowNumber == 0 || colNumber == 0) {
            val cell = StringFormCell(mContext)
            cell.setTextSize(DensityUtil.dip2Px(mContext, 18f))
            return cell
        }
        return StatusCell(mContext)
    }

    override fun getColumnCount() = mDataList[0].getSize()

    private fun getStatus(rowNumber: Int, colNumber: Int) = when (mDataList[rowNumber - 1].statusList[colNumber - 1]) {
        -1 -> {
            StatusCell.Status.CLOSED
        }
        0 -> {
            StatusCell.Status.AVAILABLE
        }
        1 -> {
            StatusCell.Status.OCCUPIED
        }
        2 -> {
            StatusCell.Status.SELECTED
        }
        else -> {
            StatusCell.Status.CLOSED
        }
    }

    data class FormData(val title: ArrayList<String>,
                        val row: Array<FormRow>) {
        override fun equals(other: Any?): Boolean {
            return super.equals(other)
        }

        override fun hashCode(): Int {
            return super.hashCode()
        }
    }

    /**
     *-1 closed
     * 0 available
     * 1 occupied
     * 2 selected
     */
    data class FormRow(
            var period: String,
            val statusList: Array<Int>
    ) {
        fun getRowContent(colNumber: Int): Any {
            if (colNumber == 0) return period
            else return statusList[colNumber - 1]
        }

        fun getSize() = statusList.size + 1

        override fun equals(other: Any?): Boolean {
            return super.equals(other)
        }

        override fun hashCode(): Int {
            return super.hashCode()
        }
    }

    fun String.isEmpty(): Boolean {
        return TextUtils.isEmpty(this)
    }
}