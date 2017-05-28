package com.graduation.design.bestellen.common

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by pan on 2017/5/21.
 * utils
 */
class Utils {
    companion object{

        val mDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        fun getTime(index: Int, start: Int = 8): String {
            val suffix: String
            if (index % 2 == 1) {
                suffix = ":30"
            } else {
                suffix = ":00"
            }
            return (index / 2 + start).toString() + suffix
        }

        fun formatDate(date:Date):String{
            return mDateFormat.format(date)
        }

        fun formatDateByCHN(date:Date):String{
            return SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault()).format(date)
        }

        fun parseByCHN(dateString: String): Date{
            return SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault()).parse(dateString)
        }
    }
}