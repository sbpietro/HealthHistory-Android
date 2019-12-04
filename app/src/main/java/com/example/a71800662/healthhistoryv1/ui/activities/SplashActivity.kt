package com.example.a71800662.healthhistoryv1.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.a71800662.healthhistoryv1.R
import com.example.a71800662.healthhistoryv1.activities.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            login()
        }, 500)
    }

    fun login(){

        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()

    }
}
