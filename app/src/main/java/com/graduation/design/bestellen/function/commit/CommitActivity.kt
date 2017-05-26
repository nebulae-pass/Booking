package com.graduation.design.bestellen.function.commit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.graduation.design.bestellen.R
import com.graduation.design.bestellen.base.BaseToolbarActivity
import com.graduation.design.bestellen.common.Utils
import com.graduation.design.bestellen.model.OccupyTime
import com.graduation.design.bestellen.model.RoomDetail
import kotlinx.android.synthetic.main.activity_commit.*

class CommitActivity : BaseToolbarActivity() {
    companion object {
        fun start(context: Context, detail: RoomDetail, uid: String, date: String, occupyTime: OccupyTime) {
            val intent = Intent(context, CommitActivity::class.java)
            intent.putExtra("detail", detail)
            intent.putExtra("uid", uid)
            intent.putExtra("date", date)
            intent.putExtra("occupy", occupyTime)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commit)
    }

    override fun initViews() {
        val detail = intent.getParcelableExtra<RoomDetail>("detail")
        val uid = intent.getStringExtra("uid")
        val date = intent.getStringExtra("date")
        val occupy = intent.getParcelableExtra<OccupyTime>("occupy")

        roomNameText.text = detail.name
        dateText.text = getString(R.string.apply_date, date)
        periodText.text = getString(R.string.apply_time, Utils.getTime(occupy.start), Utils.getTime(occupy.end))

        commitButton.setOnClickListener {

        }
    }
}
