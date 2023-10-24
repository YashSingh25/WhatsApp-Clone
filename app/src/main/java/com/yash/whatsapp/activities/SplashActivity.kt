package com.yash.whatsapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.yash.whatsapp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPreferences = getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        val isUserAlreadyLogIn = sharedPreferences.getBoolean("isUserLogin", false)

        Handler().postDelayed(Runnable {
            if(!isUserAlreadyLogIn) {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this@SplashActivity, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            }
        } , 2000L)
    }
}