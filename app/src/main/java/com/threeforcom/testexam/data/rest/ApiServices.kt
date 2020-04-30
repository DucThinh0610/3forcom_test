package com.threeforcom.testexam.data.rest

import com.threeforcom.testexam.data.rest.response.BookListResponse
import com.threeforcom.testexam.model.BookModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("volumes")
    fun getListBook(@Query("q") searchValue: String): Observable<BookListResponse>

    @GET("volumes/{volumesID}")
    fun getDetailBook(@Path("volumesID") id: String): Observable<BookModel>

}