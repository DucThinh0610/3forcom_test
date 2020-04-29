package com.threeforcom.testexam.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDAO {
    @Query("SELECT * FROM tb_book")
    fun getAll(): List<BookEntity>

    @Insert
    fun insertAll(vararg users: BookEntity)

    @Delete
    fun delete(user: BookEntity)

}