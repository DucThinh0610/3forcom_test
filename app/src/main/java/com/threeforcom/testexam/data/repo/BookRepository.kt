package com.threeforcom.testexam.data.repo

import com.threeforcom.testexam.data.local.BookDAO
import com.threeforcom.testexam.data.local.BookEntity
import com.threeforcom.testexam.data.rest.ApiServices
import com.threeforcom.testexam.data.rest.response.BookListResponse
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val apiServices: ApiServices,
    private val bookDAO: BookDAO
) {
    fun searchBook(keySearch: String = "", isLocalSearch: Boolean = true): Observable<List<BookEntity>> {
        return if (isLocalSearch) {
            bookDAO.searchBookObs(keySearch)
        } else {
            Observable.zip(
                apiServices.getListBook(keySearch),
                bookDAO.searchBookObs(keySearch),
                BiFunction<BookListResponse?, List<BookEntity>, List<BookEntity>> { t1, t2 ->
                    mergerDataRestAndLocal(
                        t1,
                        t2
                    )
                }
            )
        }
    }

    fun updateFavoriteBook(bookEntity: BookEntity): Observable<BookEntity> {
        bookEntity.isFavorite = !bookEntity.isFavorite
        return bookEntity.isFavorite.takeIf { it }?.let {
            bookDAO.insertAll(bookEntity).andThen(Observable.just(bookEntity))
        } ?: kotlin.run {
            bookDAO.delete(bookEntity).andThen(Observable.just(bookEntity))
        }
    }

    private fun mergerDataRestAndLocal(
        bookListResponse: BookListResponse?,
        localBooks: List<BookEntity>
    ): List<BookEntity> {
        return bookListResponse?.listBook?.map { bookModel ->
            val localItem = localBooks.find { item -> item.id == bookModel.id }
            BookEntity(
                id = bookModel.id,
                isFavorite = localItem?.isFavorite ?: false,
                author = bookModel.volumeInfo?.authors?.first(),
                imageURL = bookModel.volumeInfo?.imageLinks?.smallThumbnail ?: "",
                price = bookModel.saleInfo.listPrice?.amount ?: 0.0,
                retailPrice = bookModel.saleInfo.retailPrice?.amount ?: 0.0,
                title = bookModel.volumeInfo?.title ?: ""
            )
        }?.toList() ?: kotlin.run {
            localBooks
        }
    }
}