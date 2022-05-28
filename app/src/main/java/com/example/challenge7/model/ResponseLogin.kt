package com.example.challenge7.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseLogin (
    @SerializedName("username")
    val email : String,
    @SerializedName("password")
    val password : String
) : Parcelable