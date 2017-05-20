package com.graduation.design.bestellen.common

import android.text.Editable
import android.text.TextWatcher

/**
 * Created by pan on 2017/5/19.
 * CommonTextWatcher, reduce implement method number
 */
open class CommonTextWatcher : TextWatcher{
    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}