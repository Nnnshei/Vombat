package com.example.vombat.ui.clicker

interface ClickerView {
    fun showTextCounter(counter: Int, coins: Int)
    fun setButtonPic(resource: Int)
    fun tooMuchAbuse()
}