package com.graduation.design.bestellen.room

import android.content.Context
import com.nebula.wheel.FormCell
import com.nebula.wheel.FormView
import com.nebula.wheel.StringFormCell

/**
 * Created by pan on 2017/5/11.
 * form adapter
 */
class FormAdapter(context: Context, dataList: Array<RoomBookingActivity.FormRow>) : FormView.BaseAdapter<FormCell>() {
    val mContext = context
    val mDataList = dataList

    val title = Array(8) { it ->
        if (it == 0) {
            return@Array "时间段"
        }
        return@Array it.toString() + "日"
    }

    override fun bindCell(cell: FormCell, rowNumber: Int, colNumber: Int) {
        cell.isClickable = false
        if (cell is StringFormCell) {
            if (rowNumber == 0) {
                cell.content = title[colNumber]
                return
            } else {
                cell.content = mDataList[rowNumber - 1].period
            }
        }
        if (cell is StatusCell) {
            val status = getStatus(rowNumber, colNumber)
            if (status == StatusCell.Status.SELECTED||status == StatusCell.Status.AVAILABLE) {
                cell.isClickable = true
            }
            cell.setStatus(status)
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
}