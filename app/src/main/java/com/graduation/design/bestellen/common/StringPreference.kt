package com.graduation.design.bestellen.common

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by pan on 2017/6/2.
 */
class StringPreference(val context: Context, val name: String, val default: String) : ReadWriteProperty<Any, String> {
    val prefs: SharedPreferences by lazy {
        context.getSharedPreferences("only", Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return prefs.getString(name, default)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        prefs.edit().putString(name,value).apply()
    }
}