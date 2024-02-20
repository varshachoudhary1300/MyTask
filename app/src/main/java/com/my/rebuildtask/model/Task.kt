package com.my.rebuildtask.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

val customOrder = listOf("CRITICAL", "HIGH", "LOW")

@Parcelize
data class Task (
    var id: Long? = null,
    var title : String ?= null,
    var description : String ?= null,
    var priority : String ?= null
) : Parcelable