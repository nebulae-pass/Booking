package com.graduation.design.bestellen.common

/**
 * Created by pan on 2017/5/21.
 */
class Utils {
    companion object{
        fun getTime(index: Int, start: Int = 8): String {
            val suffix: String
            if (index % 2 == 1) {
                suffix = ":30"
            } else {
                suffix = ":00"
            }
            return (index / 2 + start).toString() + suffix
        }
    }
}