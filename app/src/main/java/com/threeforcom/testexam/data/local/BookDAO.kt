package com.threeforcom.testexam.data.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface BookDAO {
    @Query("SELECT * FROM tb_book")
    fun getAll(): List<BookEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg books: BookEntity) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(books: BookEntity)

    @Update
    fun updateFavoriteItem(bookEntity: BookEntity): Completable

    @Delete
    fun delete(user: BookEntity) : Completable

    @Query("SELECT * FROM tb_book WHERE title LIKE '%' || :keySearch || '%'")
    fun searchBookObs(keySearch: String): Observable<List<BookEntity>>

    @Query("SELECT * FROM tb_book WHERE title LIKE '%' || :keySearch || '%'")
    fun searchBook(keySearch: String): List<BookEntity>


}