package com.example.challenge7.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.challenge7.R
import com.example.challenge7.model.GetAllFilmItem
import com.example.challenge7.room.Film
import com.example.challenge7.room.FilmDB
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class DetailActivity : AppCompatActivity() {
    var filmfavdb : FilmDB? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        filmfavdb = FilmDB.getInstance(this)
        detailFilm()
        addtoFav()
    }

    fun detailFilm(){
        val datafilm = intent.getParcelableExtra("datafilm") as GetAllFilmItem?
        tv_detail_title.text = datafilm!!.moviename
        tv_detail_desc.text = datafilm!!.description
    }

    fun addtoFav(){
        btn_addtofav.setOnClickListener {
            GlobalScope.async {
                val detailfilm = intent.getParcelableExtra("datafilm") as GetAllFilmItem?
                val director = detailfilm!!.director
                val releasedate = detailfilm!!.date
                val synopsis = detailfilm!!.description
                val title = detailfilm!!.moviename
                val image = detailfilm!!.image
                val id = detailfilm!!.id

                runOnUiThread {
                    var checkdata = filmfavdb?.filmDAO()?.insertFilm(Film(id.toInt(),director,image,releasedate.toString(),synopsis,title))
                    if(checkdata != 0.toLong()){
                        Toast.makeText(this@DetailActivity,"Berhasil ditambahkan!", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this@DetailActivity,"Gagal ditambahkan!", Toast.LENGTH_LONG).show()
                    }
                    Log.d("oi2",checkdata.toString())
                }
            }
        }
    }
}