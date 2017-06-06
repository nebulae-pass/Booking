//package com.graduation.design.bestellen.common
//
//import android.content.Context
//import android.content.SharedPreferences
//import kotlin.properties.ReadWriteProperty
//import kotlin.reflect.KProperty
//
///**
// * Created by pan on 2017/6/6.
// */
//class CommonPreference<T>(val context: Context, val name: String, val default: T):ReadWriteProperty<Any,T> {
//    val prefs:SharedPreferences by lazy {
//        context.getSharedPreferences("default", Context.MODE_PRIVATE)
//    }
//
//    override fun getValue(thisRef: Any, property: KProperty<*>): T {
//        return Any()
//    }
//
//    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
//        prefs.edit().put
//    }
//}