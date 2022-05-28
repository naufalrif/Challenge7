package com.example.challenge7.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Film::class], version = 1)
abstract class FilmDB : RoomDatabase() {
    abstract fun filmDAO() : FilmDao

    companion object{
        private var INSTANCE : FilmDB? = null
        fun getInstance(context: Context) : FilmDB? {
            if (INSTANCE == null){
                synchronized(FilmDB::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,FilmDB::class.java,"favfilm.db")
                        .allowMainThreadQueries().fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance(){
            INSTANCE = null
        }
    }
}