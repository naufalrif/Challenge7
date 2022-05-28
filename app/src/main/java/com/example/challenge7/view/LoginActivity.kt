package com.example.challenge7.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.challenge7.R
import com.example.challenge7.datastore.UserManager
import com.example.challenge7.di.ApiClient
import com.example.challenge7.model.GetAllUserItem
import com.example.challenge7.viewmodel.ViewModelUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var userManager : UserManager
    lateinit var userLogin : List<GetAllUserItem>
    var id = ""
    var pass = ""
    var username = ""
    lateinit var datauser : List<GetAllUserItem>
    lateinit var viewmodel : ViewModelUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        userManager = UserManager(this)
        toRegister()
        doLogin()
    }

    fun loginUser(username : String, password : String){
        ApiClient.instance.getUser(username)
            .enqueue(object : Callback<List<GetAllUserItem>>{
                override fun onResponse(
                    call: Call<List<GetAllUserItem>>,
                    response: Response<List<GetAllUserItem>>
                ) {
                    if(response.isSuccessful){
                        if (response.body()?.isEmpty() == true){
                            Toast.makeText(this@LoginActivity, "User belum terdaftar", Toast.LENGTH_LONG).show()
                        }else{
                            when{
                                response.body()?.size!! > 1 -> {
                                    Toast.makeText(this@LoginActivity, "Cek kembali input data", Toast.LENGTH_LONG)
                                        .show()
                                }
                                username!=response.body()!![0].username -> {
                                    Toast.makeText(this@LoginActivity, "Username belum terdaftar", Toast.LENGTH_LONG)
                                        .show()
                                }
                                password!=response.body()!![0].password -> {
                                    Toast.makeText(this@LoginActivity, "Password salah", Toast.LENGTH_LONG)
                                        .show()
                                }
                                else -> {
                                    userLogin = response.body()!!
                                    datastoreUser(userLogin)
                                    startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<List<GetAllUserItem>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

    fun datastoreUser(listlogin : List<GetAllUserItem>){
        for (i in listlogin.indices){
            GlobalScope.launch { 
                userManager.saveData(
                    listlogin[i].id,
                    listlogin[i].username,
                    listlogin[i].password,
                    listlogin[i].email
                )
            }
        }
    }

    fun getUserData(){
        viewmodel = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewmodel.getUserLiveDataObserver().observe(this, Observer {
            datauser = it
        })
        viewmodel.userLogin()
    }

    fun toRegister(){
        tv_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    fun doLogin(){
        btn_login.setOnClickListener {
            if (et_email.text.isNotEmpty() && et_pass.text.isNotEmpty()){
                username = et_email.text.toString()
                pass = et_pass.text.toString()

                loginUser(username,pass)
            }else{
                Toast.makeText(this, "Data belum terisi", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getUserData()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}