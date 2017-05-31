package com.graduation.design.bestellen.function.search

import com.graduation.design.bestellen.R
import com.graduation.design.bestellen.base.BaseDialog
import com.graduation.design.bestellen.model.ConditionSearchData
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.*

/**
 * Created by pan on 2017/5/31.
 * ConditionSearch
 */
class ConditionSearchDialog : BaseDialog() {
    override fun getLayout(): Int {
        return R.layout.fragment_search
    }

    override fun initViews() {
        searchButton.setOnClickListener {
            var min = 0
            var max = 0
            when (capacitySpinner.selectedItemPosition) {
                0 -> {
                    min = 0
                    max = 5
                }
                1 -> {
                    min = 5
                    max = 10
                }
                2 -> {
                    min = 10
                    max = 30
                }
                3 -> {
                    min = 30
                    max = 50
                }
                4 -> {
                    min = 50
                    max = 100
                }
                5 -> {
                    min = 100
                    max = Int.MAX_VALUE
                }
            }
            val device = Arrays.asList(netCheckBox.isChecked, projectionCheckBox.isChecked, micCheckBox.isChecked)
            val data = ConditionSearchData(typeSpinner.selectedItem as String, min, max, device)
            SearchResultActivity.start(activity, data)
        }
    }
}
