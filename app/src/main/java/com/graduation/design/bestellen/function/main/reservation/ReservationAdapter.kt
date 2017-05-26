package com.graduation.design.bestellen.function.main.reservation

import android.content.Context
import com.graduation.design.bestellen.R
import com.graduation.design.bestellen.base.BaseViewHolder
import com.graduation.design.bestellen.model.RoomDetail
import com.graduation.design.bestellen.model.RoomDevice
import com.graduation.design.bestellen.view.BaseRecyclerAdapter

/**
 * Created by pan on 2017/5/6.
 * reservation adapter
 */
class ReservationAdapter(var context: Context, dataSet: MutableList<RoomDetail>) : BaseRecyclerAdapter(context) {
    val mDataSet = dataSet

    override fun setViews(viewHolder: BaseViewHolder?, position: Int) {
        val roomDetail = mDataSet[position]
        viewHolder?.setText(R.id.room_name, roomDetail.name)
                ?.setText(R.id.location, roomDetail.location)
                ?.setText(R.id.type, roomDetail.type)
                ?.setText(R.id.capacity, roomDetail.capacity.toString())
                ?.setVisibility(R.id.net_enable, roomDetail.deviceEnable[RoomDevice.net])
                ?.setVisibility(R.id.projection_enable, roomDetail.deviceEnable[RoomDevice.projection])
                ?.setVisibility(R.id.mic_enable, roomDetail.deviceEnable[RoomDevice.microphone])
    }

    override fun getItemCount(): Int = mDataSet.size

    override fun getItemViewType(position: Int): Int = R.layout.item_reservation

    fun getDataSet(): MutableList<RoomDetail> {
        return mDataSet
    }
}