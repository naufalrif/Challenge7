package com.example.challenge7.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.challenge7.R
import com.example.challenge7.viewmodel.ViewModelUser
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    lateinit var viewmodeluser: ViewModelUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        doRegister()

    }

    fun doRegister() {
        btn_reg.setOnClickListener {
            if (et_reg_email.text.toString().isEmpty() && et_reg_username.text.toString()
                    .isEmpty() && et_reg_pass.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "Data belum terisi!", Toast.LENGTH_LONG).show()
            } else {
                if (et_reg_pass.text.toString() != et_reg_pass_repeat.text.toString()) {
                    Toast.makeText(this, "Password tidak sama!", Toast.LENGTH_LONG)
                        .show()
                } else {
                    val username = et_reg_username.text.toString()
                    val email = et_reg_email.text.toString()
                    val password = et_reg_pass.text.toString()
                    registerUser(username,email, password)
                    startActivity(Intent(this,LoginActivity::class.java))


                }
            }
        }
    }

    fun registerUser(username: String, email: String, password: String) {
        viewmodeluser = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewmodeluser.userRegister(username, password, email)
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(this, "Registrasi sukses", Toast.LENGTH_LONG)
                .show()
        }
    }
}