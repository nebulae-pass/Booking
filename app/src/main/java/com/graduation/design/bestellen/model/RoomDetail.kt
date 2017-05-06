package com.graduation.design.bestellen.model

/**
 * Created by pan on 2017/5/4.
 */
data class RoomDetail(var id: Int,
                      var name: String,
                      var capacity: Int,
                      var local: String,
                      var append: String)

data class ApplyInfo(var start: String,
                     var end: String,
                     var applyAppend: String)
