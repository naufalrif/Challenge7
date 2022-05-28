package com.example.challenge7.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge7.di.ApiServices
import com.example.challenge7.model.GetAllFilmItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModelFilm @Inject constructor(api : ApiServices): ViewModel() {
    private var livedataFilm = MutableLiveData<List<GetAllFilmItem>>()
    val film : LiveData<List<GetAllFilmItem>> = livedataFilm

//    fun getfilmLiveData(): MutableLiveData<List<GetAllFilmItem>> {
//        return livedataFilm
//    }

    init {
        viewModelScope.launch {
            val datafilm = api.getAllFilm()
            delay(2000)
            livedataFilm.value = datafilm
        }
    }

//    fun getFilmApi(){
//        ApiClient.instance.getAllFilm()
//            .enqueue(object : Callback<List<GetAllFilmItem>> {
//                override fun onResponse(
//                    call: Call<List<GetAllFilmItem>>,
//                    response: Response<List<GetAllFilmItem>>
//                ) {
//                    if (response.isSuccessful){
//                        livedataFilm.postValue(response.body())
//                    }else{
//                        livedataFilm.postValue(null)
//                    }
//                }
//
//                override fun onFailure(call: Call<List<GetAllFilmItem>>, t: Throwable) {
//                    livedataFilm.postValue(null)
//                }
//
//            })
//    }
}