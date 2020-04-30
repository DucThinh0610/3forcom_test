package com.threeforcom.testexam.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tb_book")
data class BookEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "author")
    var author: String?,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,
    @ColumnInfo(name = "price")
    var price: Double = 0.0,
    @ColumnInfo(name = "retailPrice")
    var retailPrice: Double = 0.0,
    @ColumnInfo(name = "imageURL")
    var imageURL: String
) : Serializable {
    override fun equals(other: Any?): Boolean {
        return if (other is BookEntity) {
            return other.id == this.id
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}