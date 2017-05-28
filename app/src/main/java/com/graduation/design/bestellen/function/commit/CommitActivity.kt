package com.graduation.design.bestellen.function.commit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.graduation.design.bestellen.R
import com.graduation.design.bestellen.base.BaseToolbarActivity
import com.graduation.design.bestellen.common.LoadingDialog
import com.graduation.design.bestellen.common.Utils
import com.graduation.design.bestellen.model.OccupyTime
import com.graduation.design.bestellen.model.ReservationData
import com.graduation.design.bestellen.model.RoomDetail
import kotlinx.android.synthetic.main.activity_commit.*

class CommitActivity : BaseToolbarActivity(), CommitContract.View {
    val loadingDialog = LoadingDialog()
    val mPresenter = CommitPresenter(this, CommitData())

    companion object {
        fun startForResult(context: AppCompatActivity, detail: RoomDetail, uid: String, date: String, occupyTime: OccupyTime) {
            val intent = Intent(context, CommitActivity::class.java)
            intent.putExtra("detail", detail)
            intent.putExtra("uid", uid)
            intent.putExtra("date", date)
            intent.putExtra("occupy", occupyTime)
            context.startActivityForResult(intent, 0)
        }
    }

    override fun setPresenter(presenter: CommitContract.Presenter) {

    }

    override fun showError(string: String) {
        errorText.text = string
    }

    override fun finishActivity() {
        setResult(Activity.RESULT_OK)
        this.finish()
    }

    override fun showProgressDialog(isShown: Boolean) {
        if (isShown) {
            loadingDialog.showDialog(supportFragmentManager)
        } else {
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
            mPresenter.commitReservation(ReservationData(detail.rid, uid, contentEdit.text.toString(), date, occupy))
        }
    }
}
