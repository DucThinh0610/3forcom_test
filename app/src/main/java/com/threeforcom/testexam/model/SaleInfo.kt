package com.threeforcom.testexam.model


import com.google.gson.annotations.SerializedName

data class SaleInfo(
    @SerializedName("listPrice")
    val listPrice: ListPrice?,
    @SerializedName("retailPrice")
    val retailPrice: ListPrice?
)