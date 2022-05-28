package com.example.challenge7.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge7.di.ApiClient
import com.example.challenge7.di.ApiServices
import com.example.challenge7.model.GetAllUserItem
import com.example.challenge7.model.ResponseRegister
import com.example.challenge7.model.ResponseUpdate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class ViewModelUser : ViewModel() {
    private var livedataUser = MutableLiveData<List<GetAllUserItem>>()
    lateinit var livedataUpdate : MutableLiveData<ResponseUpdate>

//    init {
//        viewModelScope.launch {
//            val datauser = api.
//            delay(2000)
//            livedataUser.value = datafilm
//        }
//    }

    fun getUserLiveDataObserver():MutableLiveData<List<GetAllUserItem>>{
        return livedataUser
    }

    fun userLogin(){
        ApiClient.instance.getAllUser()
            .enqueue(object : Callback<List<GetAllUserItem>>{
                override fun onResponse(
                    call: Call<List<GetAllUserItem>>,
                    response: Response<List<GetAllUserItem>>
                ) {
                    if (response.isSuccessful){
                        livedataUser.postValue(response.body())
                    }else{
                        livedataUser.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<GetAllUserItem>>, t: Throwable) {
                    livedataUser.postValue(null)
                }

            })
    }

    fun userRegister(username : String, password : String, email : String) : Boolean{
        var messageResponse = false
        ApiClient.instance.postUser(ResponseRegister(email, password, username))
            .enqueue(object : Callback<GetAllUserItem>{
                override fun onResponse(
                    call: Call<GetAllUserItem>,
                    response: Response<GetAllUserItem>
                ) {
                    messageResponse = response.isSuccessful
                }

                override fun onFailure(call: Call<GetAllUserItem>, t: Throwable) {
                    messageResponse = false
                }

            })
        return messageResponse
    }
}