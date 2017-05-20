package com.graduation.design.bestellen.common

import com.graduation.design.bestellen.R
import com.graduation.design.bestellen.base.BaseDialog
import kotlinx.android.synthetic.main.dialog_loading.*

/**
 * Created by pan on 2017/5/19.
 * loading dialog
 */
class LoadingDialog(resId:Int = R.string.operation_accessing) : BaseDialog() {
    var mResId = resId

    override fun getLayout() = R.layout.dialog_loading

    override fun initViews() {
        isCancelable = false
        loadingTipText.setText(mResId)
    }
}