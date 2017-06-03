package com.graduation.design.bestellen.data

import android.content.Context
import com.graduation.design.bestellen.common.DelegatesExt

/**
 * Created by pan on 2017/6/2.
 */
class LocalDataRepository(val context: Context) {
    var account: String by DelegatesExt.ApplicationPreference(context, "account", "")
    var name: String by DelegatesExt.ApplicationPreference(context, "name", "")
    var password: String by DelegatesExt.ApplicationPreference(context, "password", "")
}