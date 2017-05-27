package com.graduation.design.bestellen.function.commit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.graduation.design.bestellen.R
import com.graduation.design.bestellen.base.BaseToolbarActivity
import com.graduation.design.bestellen.common.LoadingDialog
import com.graduation.design.bestellen.common.Utils
import com.graduation.design.bestellen.model.ApplyInfo
import com.graduation.design.bestellen.model.OccupyTime
import com.graduation.design.bestellen.model.RoomDetail
import kotlinx.android.synthetic.main.activity_commit.*
import java.util.*

class CommitActivity : BaseToolbarActivity(),CommitContract.View {
    val loadingDialog = LoadingDialog()
    val mPresenter = CommitPresenter(this, CommitData())

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

    override fun setPresenter(presenter: CommitContract.Presenter) {

    }

    override fun finishActivity() {
        finish()
    }

    override fun showProgressDialog(isShown: Boolean) {
        if (isShown) {
            loadingDialog.showDialog(supportFragmentManager)
        }else{
            loadingDialog.dismiss()
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
        periodText.text = getString(R.string.apply_time, Utils.getTime(occupy.start), Utils.getTime(occupy.end + 1))

        commitButton.setOnClickListener {
            mPresenter.commitReservation(ApplyInfo(uid, detail.rid, Date(), occupy, contentEdit.text.toString()))
        }
    }
}
