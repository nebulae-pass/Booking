package com.graduation.design.bestellen.common

import android.content.Context

/**
 * Created by pan on 2017/6/2.
 */
object DelegatesExt {
    fun ApplicationPreference(context: Context, name: String, default: String) =
            StringPreference(context, name, default)
}