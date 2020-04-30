package com.threeforcom.testexam.data.rest.response

import com.google.gson.annotations.SerializedName
import com.threeforcom.testexam.model.BookModel

data class BookListResponse(
    @SerializedName("kind")
    val kind: String,
    @SerializedName("totalItems")
    var totalItems: Int = 0,
    @SerializedName("items")
    var listBook: List<BookModel> = arrayListOf()
)