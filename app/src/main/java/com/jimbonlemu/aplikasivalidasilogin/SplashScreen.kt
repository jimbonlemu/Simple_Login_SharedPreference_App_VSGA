package com.jimbonlemu.aplikasivalidasilogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {

    private val splashTimeout: Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        Handler().postDelayed(
            {
                Get.offAll(this, LoginScreen::class.java)

            }, splashTimeout
        )
    }
}
