package com.example.vinos_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.vinos_app.R



class SplashActivity : AppCompatActivity() {

        private val SPLASH_TIME_OUT:Long = 1000 // 3 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(

            {
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }
            , SPLASH_TIME_OUT)
    }
}