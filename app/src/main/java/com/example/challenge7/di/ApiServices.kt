package com.example.challenge7.di

import com.example.challenge7.model.GetAllFilmItem
import com.example.challenge7.model.GetAllUserItem
import com.example.challenge7.model.ResponseLogin
import com.example.challenge7.model.ResponseRegister
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {
    @GET("movies")
    suspend fun getAllFilm() : List<GetAllFilmItem>

//    @POST("user")
//    @FormUrlEncoded
//    fun userPostRegister(
//        @Field("username")username : String,
//        @Field("email")email : String,
//        @Field("password")password : String
//    ) : Call<GetAllUserItem>
//
//    @POST("user")
//    @FormUrlEncoded
//    fun loginUser(
//        @Field("email") email : String,
//        @Field("password") password : String
//    ) : Call<ResponseLogin>

    @GET("user")
    fun getAllUser() : Call<List<GetAllUserItem>>

    @GET("user")
    fun getUser(
        @Query("username")username : String
    ) : Call<List<GetAllUserItem>>

    @PUT("user/{id}")
    @FormUrlEncoded
    fun updateUser(
        @Path("id")id : String,
        @Field("username")username : String,
        @Field("email")email: String,
        @Field("password")password: String
    ) : Call<ResponseRegister>

    @POST("user")
    fun postUser(@Body reqUser : ResponseRegister) : Call<GetAllUserItem>

}