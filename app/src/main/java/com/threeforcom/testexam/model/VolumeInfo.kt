package com.threeforcom.testexam.model


import com.google.gson.annotations.SerializedName

data class VolumeInfo(
    @SerializedName("allowAnonLogging")
    val allowAnonLogging: Boolean?,
    @SerializedName("authors")
    val authors: List<String> = arrayListOf(),
    @SerializedName("averageRating")
    val averageRating: Double?,
    @SerializedName("canonicalVolumeLink")
    val canonicalVolumeLink: String?,
    @SerializedName("categories")
    val categories: List<String>?,
    @SerializedName("contentVersion")
    val contentVersion: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("imageLinks")
    val imageLinks: ImageLinks,
    @SerializedName("infoLink")
    val infoLink: String?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("maturityRating")
    val maturityRating: String?,
    @SerializedName("pageCount")
    val pageCount: Int?,
    @SerializedName("previewLink")
    val previewLink: String?,
    @SerializedName("printType")
    val printType: String?,
    @SerializedName("publishedDate")
    val publishedDate: String?,
    @SerializedName("publisher")
    val publisher: String?,
    @SerializedName("ratingsCount")
    val ratingsCount: Int?,
    @SerializedName("title")
    val title: String = ""
)