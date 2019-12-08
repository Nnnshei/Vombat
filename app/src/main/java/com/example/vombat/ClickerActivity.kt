package com.example.vombat

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_clicker.*

class ClickerActivity : AppCompatActivity() {

    private lateinit var pref: SharedPreferences
    private val APP_PREFENCES = "mydata"
    private val APP_PREFERENCES_COUNTER = "counter"
    private val APPP_PREFERENCES_COIN = "coin"
    private var counter: Int = 0
    private var coin: Int = 0
    private var i: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clicker)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        pref = getSharedPreferences(APP_PREFENCES, Context.MODE_PRIVATE)
        btnCount.setOnClickListener {
            onClickCounter()
        }
        abuseCoin.setOnClickListener {
            abuseCoin()
        }
    }

    fun showTextCounter() {
        textCounter.text = "Ты нажал на кнопку ${counter} раз"
        textCoin.text = "У тебя ${coin} монет"
    }

    fun onClickCounter() {
        counter++
        coin++
        showTextCounter()
        picButton()
    }

    fun picButton() {
/*        when (counter) {
            100 -> btnCount.setImageResource(R.drawable.image1)
            200 -> btnCount.setImageResource(R.drawable.image2)
        }*/

        /*if (counter >= 15) {
            btnCount.setImageResource(R.drawable.image1)
            if (counter >= 25)
                btnCount.setImageResource(R.drawable.image2)
        }
        else btnCount.setImageResource(R.drawable.button_count)*/
    }

    fun abuseCoin() {
        counter = 0
        showTextCounter()
        picButton()
        /* i++
         if (i == 1)
             coin += 100
         textCoin.text = "У тебя ${coin} монет"
         if (i == 2)
             coin += 1000
         textCoin.text = "У тебя ${coin} монет"
         if (i == 3)
             coin += 10000
         textCoin.text = "У тебя ${coin} монет"
         if (i == 4)
             coin += 10000
         textCoin.text = "У тебя ${coin} монет"
         if (i > 4) {
             i = 1
             coin -= 111100
             textCoin.text = "У тебя ${coin} монет"
             Toast.makeText(
                 this,
                 "Отложив в скорби о том, чего у вас нет, научимся воздавать благодарность за то, что есть.",
                 Toast.LENGTH_LONG
             ).show()
         }*/
    }

    override fun onPause() {
        super.onPause()
        val editor = pref.edit()
        editor.putInt(APPP_PREFERENCES_COIN, coin)
        editor.apply()
        editor.putInt(APP_PREFERENCES_COUNTER, counter)
        editor.apply()
    }

    override fun onResume() {
        super.onResume()
        if (pref.contains(APPP_PREFERENCES_COIN))
            coin = pref.getInt(APPP_PREFERENCES_COIN, 0)

        if (pref.contains(APP_PREFERENCES_COUNTER))
            counter = pref.getInt(APP_PREFERENCES_COUNTER, 0)
        showTextCounter()
        picButton()
    }
}