package com.example.challenge7.model


import com.google.gson.annotations.SerializedName

data class GetAllUserItem(
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)