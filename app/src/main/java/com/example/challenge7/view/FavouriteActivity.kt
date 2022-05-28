package com.example.challenge7.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge7.R
import com.example.challenge7.adapter.FavAdapter
import com.example.challenge7.room.FilmDB
import kotlinx.android.synthetic.main.activity_favourite.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavouriteActivity : AppCompatActivity() {
    var filmDB : FilmDB? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite)
        filmDB = FilmDB.getInstance(this)
        getFav()
    }

    fun getFav(){

        rv_favfilm.layoutManager = LinearLayoutManager(this)

        GlobalScope.launch {
            val listFavFilm = filmDB?.filmDAO()?.getAllFilmDao()
            runOnUiThread{
                if(listFavFilm?.size!! < 1){
                    tv_favinit.setText("Belum ada film favorit!")
                }else{
                    listFavFilm.let {
                        rv_favfilm.adapter = FavAdapter(it!!)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getFav()
    }

    override fun onDestroy() {
        super.onDestroy()
        getFav()
    }
}