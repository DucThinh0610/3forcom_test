package com.threeforcom.testexam.model


import com.google.gson.annotations.SerializedName

data class ListPrice(
    @SerializedName("amount")
    val amount: Double = 0.0,
    @SerializedName("currencyCode")
    val currencyCode: String = ""
)