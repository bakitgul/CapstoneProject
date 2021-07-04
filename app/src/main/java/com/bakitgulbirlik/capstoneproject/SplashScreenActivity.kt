package com.bakitgulbirlik.capstoneproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val background = object : Thread() {
            override fun run() {
                try {
                    //threaad 5 sn yani 3000 ms uyusun
                    Thread.sleep(3000)
                    //intent ile splash ekranından sonra MainActivity ekranı açılsın diyoruz
                    val intent = Intent(baseContext, LogInMainActivity::class.java)
                    startActivity(intent)
                }catch (e : Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
 }
