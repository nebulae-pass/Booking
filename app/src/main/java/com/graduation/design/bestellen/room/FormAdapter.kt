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
    val title = Array(8){it->
        if (it == 0) {
            return@Array "时间段"
        }
        return@Array it.toString()+"日"
    }

    override fun bindCell(cell: FormCell?, rowNumber: Int, colNumber: Int) {
        if (cell is StringFormCell) {
            if (rowNumber == 0) {
                cell.content = title[colNumber]
                return
            } else {
                cell.content = mDataList[rowNumber - 1].period
            }
        }
        if (cell is StatusCell) {
            val status: StatusCell.Status
            when (mDataList[rowNumber - 1].statusList[colNumber - 1]) {
                -1 -> {
                    status = StatusCell.Status.CLOSED
                }
                0 -> {
                    status = StatusCell.Status.AVAILABLE
                }
                1 -> {
                    status = StatusCell.Status.OCCUPIED
                }
                2 -> {
                    status = StatusCell.Status.SELECTED
                }
                else -> {
                    status = StatusCell.Status.CLOSED
                }
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

    override fun getColumnCount() = 2
}