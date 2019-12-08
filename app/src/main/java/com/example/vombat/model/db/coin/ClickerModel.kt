package com.example.vombat.model.db.coin

interface ClickerModel {
    fun putCoins(coins: Int)
    fun getCoins(): Int
    fun putCounter(counter: Int)
    fun getCounter(): Int
}