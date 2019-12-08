package com.example.vombat.model.db.coin

import android.content.SharedPreferences

class ClickerModelImpl(private val pref: SharedPreferences) : ClickerModel {

    private val APP_PREFERENCES_COUNTER = "counter"
    private val APP_PREFERENCES_COIN = "coin"

    override fun putCoins(coins: Int) {
        val editor = pref.edit()
        editor.putInt(APP_PREFERENCES_COIN, coins)
        editor.apply()
    }

    override fun getCoins(): Int = pref.getInt(APP_PREFERENCES_COIN, 0)

    override fun putCounter(counter: Int) {
        val editor = pref.edit()
        editor.putInt(APP_PREFERENCES_COUNTER, counter)
        editor.apply()
    }

    override fun getCounter() = pref.getInt(APP_PREFERENCES_COUNTER, 0)
}