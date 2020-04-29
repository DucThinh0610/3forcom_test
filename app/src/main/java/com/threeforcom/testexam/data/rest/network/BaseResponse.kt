package com.threeforcom.testexam.data.rest.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("code")
    @Expose
    var code: Int = 0
    @SerializedName("error")
    @Expose
    var message: String = ""

    @SerializedName("status")
    @Expose
    var status: String? = null
}
