package com.example.challenge7.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FilmDao {
    @Insert
    fun insertFilm(film: Film) : Long

    @Query("SELECT * FROM Film")
    fun getAllFilmDao() : List<Film>

    @Delete
    fun deleteFilm(film: Film) : Int
}