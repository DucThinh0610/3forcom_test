package com.threeforcom.testexam.data.repo

import com.threeforcom.testexam.data.local.BookDAO
import com.threeforcom.testexam.data.rest.ApiServices
import javax.inject.Inject

class BookRepository @Inject constructor(val apiServices: ApiServices, val bookDAO: BookDAO) {

}