package com.example.challenge7.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challenge7.R
import com.example.challenge7.datastore.UserManager
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {
    lateinit var userManager: UserManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        userManager = UserManager(this)
        logout()
    }

    fun logout(){
        btn_logout_profile.setOnClickListener {
            GlobalScope.launch {
                userManager.clearData()
            }
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}