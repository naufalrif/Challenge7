package com.example.challenge7.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
@Entity
@Parcelize
data class Film (
    @PrimaryKey()
    val id : Int?,
    @ColumnInfo(name = "director")
    var director : String,
    @ColumnInfo(name = "image")
    var image : String,
    @ColumnInfo(name = "release_date")
    var  releasedate : String,
    @ColumnInfo(name = "synopsis")
    var synopsis : String,
    @ColumnInfo(name = "title")
    var title : String
) : Parcelable
