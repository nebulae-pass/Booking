package com.graduation.design.bestellen.common

import android.util.Log
import com.graduation.design.bestellen.BuildConfig

/**
 * Created by pan on 2017/5/20.
 * used to log
 */
class Logs {
    companion object {
        var show :Boolean = true
        fun init() {
            show = BuildConfig.DEBUG
        }

        fun v(message: String) {
            toLog(1, message)
        }

        fun e(message: String) {
            toLog(2, message)
        }

        fun toLog(type:Int, message: String) {
            if (!show) {
                return
            }
            val stackTrace = Thread.currentThread().stackTrace

            val index = 4
            val className = stackTrace[index].fileName
            var methodName = stackTrace[index].methodName
            val lineNumber = stackTrace[index].lineNumber

            val tag = className
            methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1)

            val stringBuilder = StringBuilder()
            stringBuilder.append("[ (").append(className).append(":").append(lineNumber).append(")#").append(methodName).append(" ] ")
            stringBuilder.append(message)
            when (type) {
                1->{
                    Log.v(tag, stringBuilder.toString())
                }
                2 ->{
                    Log.e(tag, stringBuilder.toString())
                }
            }
        }
    }
}
