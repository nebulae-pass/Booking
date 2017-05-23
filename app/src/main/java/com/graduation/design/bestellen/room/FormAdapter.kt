package com.graduation.design.bestellen.room

import android.content.Context
import com.nebula.wheel.FormCell
import com.nebula.wheel.FormView
import com.nebula.wheel.StringFormCell
import java.util.*

/**
 * Created by pan on 2017/5/11.
 * form adapter
 */
class FormAdapter(context: Context, data:FormData) : FormView.BaseAdapter<FormCell>() {
    val mData = data
    val mContext = context
    val mDataList = data.row

    val mTitle = data.title
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
                if (cell.getStatus() == StatusCell.Status.SELECTED) {
                    cell.setStatus(StatusCell.Status.AVAILABLE)
                    return@setOnCellClickListener
                }
                if (cell.getStatus() == StatusCell.Status.AVAILABLE) {
                    cell.setStatus(StatusCell.Status.SELECTED)
                }

            }
        }
    }

    override fun getRowCount() = mDataList.size + 1

    override fun createCell(rowNumber: Int, colNumber: Int): FormCell {
        if (rowNumber == 0 || colNumber == 0) {
            return StringFormCell(mContext)
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
                        val row: Array<FormRow>){
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
}