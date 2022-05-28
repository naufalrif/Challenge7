package com.example.challenge7.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge7.R
import com.example.challenge7.adapter.FilmAdapter
import com.example.challenge7.datastore.UserManager
import com.example.challenge7.viewmodel.ViewModelFilm
import dagger.hilt.android.AndroidEntryPoint
//import dagger.hiAndroidEntryPolt.android.HiltAndroidApp
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var filmAdapter: FilmAdapter
    lateinit var usermanager : UserManager
    lateinit var username : String
    lateinit var id : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usermanager = UserManager(this)

        usermanager.userUsername.asLiveData().observe(this,{
            this.username = it.toString()
            tv_welcomeuser.setText(username)
        })

        filmAdapter = FilmAdapter {
            val pindah = Intent(this, DetailActivity::class.java)
            pindah.putExtra("datafilm",it)
            startActivity(pindah)
        }
        rv_film.layoutManager = LinearLayoutManager(this)
        rv_film.adapter = filmAdapter
//        initRV()
        getFilm()
        btn_profile.setOnClickListener {
            startActivity(Intent(this,ProfileActivity::class.java))
        }
        btn_fav.setOnClickListener {
            startActivity(Intent(this,FavouriteActivity::class.java))
        }
        runOnUiThread {  }
    }

    fun initRV(){

//        filmAdapter = FilmAdapter {
//
//        }
        getFilm()
    }
    fun getFilm(){
        val viewmodel = ViewModelProvider(this).get(ViewModelFilm::class.java)

        viewmodel.film.observe(this,{
            if (it != null){
                filmAdapter.setDataFilm(it)
                filmAdapter.notifyDataSetChanged()
            }
        })
    }
}
