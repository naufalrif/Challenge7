package com.example.challenge7.adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challenge7.R
import com.example.challenge7.room.Film
import com.example.challenge7.room.FilmDB
import com.example.challenge7.view.FavouriteActivity
import kotlinx.android.synthetic.main.item_film_fav.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class FavAdapter(val listfav : List<Film>) : RecyclerView.Adapter<FavAdapter.ViewHolder>() {
    var filmfavdb : FilmDB? = null
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavAdapter.ViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.item_film_fav, parent, false)

        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: FavAdapter.ViewHolder, position: Int) {
        this.let{
            Glide.with(holder.itemView.context).load(listfav!![position].image).into(holder.itemView.img_film_fav)
        }
        holder.itemView.tv_judulfilmfav.text = listfav[position].title
        holder.itemView.tv_tanggalfilmfav.text= listfav[position].releasedate
        holder.itemView.tv_sutradarafilmfav.text = listfav[position].director
        holder.itemView.btn_deletefav.setOnClickListener {
            filmfavdb = FilmDB.getInstance(it.context)
            val alertbuilder = AlertDialog.Builder(it.context)
                .setTitle("Hapus film favorit")
                .setMessage("Hapus film dari list favorit?")
                .setPositiveButton("Ya"){ dialogInterface : DialogInterface, i : Int ->
                    GlobalScope.async {
                        val deleteresult= filmfavdb?.filmDAO()?.deleteFilm(listfav[position])
                        (holder.itemView.context as FavouriteActivity).runOnUiThread {
                            if(deleteresult != null){
                                Toast.makeText(it.context,"Berhasil dihapus", Toast.LENGTH_LONG)
                                    .show()
                            }else{
                                Toast.makeText(it.context, "Gagal menghapus", Toast.LENGTH_LONG ).show()
                            }
                        }
                    }
                }
                .setNegativeButton("Tidak"){ dialogInterface : DialogInterface, i : Int ->
                    dialogInterface.dismiss()
                }
                .show()
        }
    }

    override fun getItemCount(): Int {
        return listfav.size
    }

}