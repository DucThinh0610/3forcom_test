package com.threeforcom.testexam.model


import com.google.gson.annotations.SerializedName

data class ListPrice(
    @SerializedName("amount")
    var amount: Double = 0.0,
    @SerializedName("currencyCode")
    var currencyCode: String = ""
)