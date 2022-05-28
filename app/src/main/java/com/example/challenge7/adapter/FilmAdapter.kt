package com.example.challenge7.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challenge7.R
import com.example.challenge7.model.GetAllFilmItem
import kotlinx.android.synthetic.main.item_film.view.*

class FilmAdapter(private var onCLick : (GetAllFilmItem)->Unit) : RecyclerView.Adapter<FilmAdapter.ViewHolder>(){
    private var datafilm : List<GetAllFilmItem>? = null

    fun setDataFilm(film : List<GetAllFilmItem>){
        this.datafilm = film
    }

    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmAdapter.ViewHolder {
        val itemview = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_film, parent, false)
        return ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: FilmAdapter.ViewHolder, position: Int) {
        holder.itemView.tv_judulfilm.text = datafilm!![position].moviename
        holder.itemView.tv_tanggalfilm.text = datafilm!![position].date.toString()
        holder.itemView.tv_sutradarafilm.text = datafilm!![position].director
        Glide.with(holder.itemView.context).load(datafilm!![position].image).into(holder.itemView.img_film)
        holder.itemView.cardfilm.setOnClickListener {
            onCLick(datafilm!![position])
        }
    }

    override fun getItemCount(): Int {
        if (datafilm == null){
            return 0
        }
        else{
            return datafilm?.size!!
        }
    }
}