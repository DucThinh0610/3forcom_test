package com.threeforcom.testexam

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.threeforcom.testexam.data.local.AppDatabase
import com.threeforcom.testexam.data.local.BookDAO
import com.threeforcom.testexam.data.local.BookEntity
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RoomDBTest {
    private lateinit var bookDAO: BookDAO
    private lateinit var db: AppDatabase
    val testBookEntity = BookEntity(
        id = "1",
        title = "title",
        isFavorite = false,
        author = "author",
        retailPrice = 123.0,
        price = 1203.1,
        imageURL = ""
    )
    val testBookEntity2 = BookEntity(
        id = "2",
        title = "BookEntity",
        isFavorite = false,
        author = "Nguyen Nhat Anh",
        retailPrice = 123.0,
        price = 1203.1,
        imageURL = ""
    )

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        bookDAO = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeAndReadWithRX() {
        bookDAO.insertItem(testBookEntity)
        val bookFound = bookDAO.searchBookObs("title").test().values().first()[0]
        assertEquals("writeBookAndReadInList", bookFound, testBookEntity)
    }

    @Test
    @Throws(Exception::class)
    fun writeAndRead() {
        bookDAO.insertItem(testBookEntity)
        val foundItem = bookDAO.getAll().find { item -> item.title == testBookEntity.title }
        assertEquals("writeBookAndReadInList", foundItem, testBookEntity)
    }

}
