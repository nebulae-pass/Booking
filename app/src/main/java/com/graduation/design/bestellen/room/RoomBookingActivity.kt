package com.graduation.design.bestellen.room

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.view.View
import com.graduation.design.bestellen.R
import com.graduation.design.bestellen.base.BaseActivity
import com.graduation.design.bestellen.model.DailyRoomOccupation
import com.graduation.design.bestellen.model.OccupyTime
import com.graduation.design.bestellen.model.RoomDetail
import com.graduation.design.bestellen.model.RoomDevice
import kotlinx.android.synthetic.main.activity_booking.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class RoomBookingActivity : BaseActivity(), RoomBookingContract.View {
    /**
     * cache status bar height
     */
    var mStatusBarHeight: Int = 0
    var mAdapter: FormAdapter? = null

    override fun getDataSet() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
    }

    override fun initViews() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

//        if (intent.extras != null && intent.extras.containsKey("detail")) {
//            val detail: RoomDetail = intent.extras["detail"] as RoomDetail
        val detail = RoomDetail("1","逸夫楼503","教室",20,"南区逸夫楼","",mutableListOf(true,true,true),"")
            titleText.text = detail.name
            locationText.text = detail.location
            appendText.text = detail.append
            capacityText.text = detail.capacity.toString()
            netEnableIcon.visibility = if (detail.deviceEnable[RoomDevice.net]) View.VISIBLE else View.GONE
            projectionEnableIcon.visibility = if (detail.deviceEnable[RoomDevice.projection]) View.VISIBLE else View.GONE
            micEnableIcon.visibility = if (detail.deviceEnable[RoomDevice.microphone]) View.VISIBLE else View.GONE
//        }

        mStatusBarHeight = getStatusBarHeight()
        formView.post {
            var marginTop = selector.height
            (colorDescription.layoutParams as CoordinatorLayout.LayoutParams).topMargin = marginTop
            marginTop += colorDescription.height
            (formView.layoutParams as CoordinatorLayout.LayoutParams).topMargin = marginTop
            marginTop = supportActionBar?.height as Int + mStatusBarHeight
            (detailLayout.layoutParams as CollapsingToolbarLayout.LayoutParams).topMargin = marginTop
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                appbar.stateListAnimator = null
            }
        }

        val occupyList = ArrayList<OccupyTime>()
        occupyList.add(OccupyTime(0, 3))
        occupyList.add(OccupyTime(5, 10))


        val dataList = ArrayList <DailyRoomOccupation>()
        dataList.add(DailyRoomOccupation("a", "a", occupyList, OccupyTime(0, 20)))
        dataList.add(DailyRoomOccupation("a", "a", occupyList, OccupyTime(0, 20)))
        dataList.add(DailyRoomOccupation("a", "a", occupyList, OccupyTime(0, 20)))
        dataList.add(DailyRoomOccupation("a", "a", occupyList, OccupyTime(0, 20)))
        dataList.add(DailyRoomOccupation("a", "a", occupyList, OccupyTime(0, 20)))
        dataList.add(DailyRoomOccupation("a", "a", occupyList, OccupyTime(0, 20)))
        dataList.add(DailyRoomOccupation("a", "a", occupyList, OccupyTime(0, 20)))

        mAdapter = FormAdapter(this, getFormDataBy(dataList))
        formView.setAdapter(mAdapter)
    }


    override fun initListener() {
        fab.setOnClickListener { view ->
        }
        appbar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            val fraction = verticalOffset / (supportActionBar?.height!! - appBarLayout.height + mStatusBarHeight)
            val elevation = 8f * fraction //12f or 0f
            ViewCompat.setElevation(selector, elevation)
            ViewCompat.setElevation(appbar, elevation)
        }
        val sDateFormat = SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault())
        selector.text = sDateFormat.format(java.util.Date())
        selector.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(this,
                    { _, year, month, dayOfMonth ->
                        selector.text = getString(R.string.date_format, year.toString(), (month + 1).toString(), dayOfMonth.toString())
                    }, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH])
                    .show()
        }
    }

    fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    fun getFormDataBy(list: List<DailyRoomOccupation>): Array<FormRow> {

        val data: Array<FormRow> = Array(28) { FormRow("", Array(7){0}) }
        for (j in 0..6) {
            val oc = list[j]
            val occupation = oc.occupyList
            for (i in 0..oc.openingTime.start) {
                data[i].statusList[j] = -1
            }
            for (i in oc.openingTime.end..27) {
                data[i].statusList[j] = -1
            }
            occupation.forEach {
                for (i in it.start..it.end - 1) {
                    data[i].statusList[j] = 1
                }
            }
            for (i in 0..27) {
                data[i].period = getTime(i)
            }
        }
        return data
    }


    fun getTime(index: Int, start: Int = 8): String {
        val suffix: String
        if (index % 2 == 1) {
            suffix = ":30"
        } else {
            suffix = ":00"
        }
        return (index / 2 + start).toString() + suffix
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
    }

}
