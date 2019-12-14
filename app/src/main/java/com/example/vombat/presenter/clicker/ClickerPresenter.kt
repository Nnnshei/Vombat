package com.example.vombat.presenter.clicker

import com.example.vombat.R
import com.example.vombat.model.db.coin.ClickerModel
import com.example.vombat.ui.clicker.ClickerView

class ClickerPresenter(private val view: ClickerView, private val model: ClickerModel) {
    private var counter = 0
    private var coins = 0
    private var i = 0

    fun fetchData() {
        counter = model.getCounter()
        coins = model.getCoins()
    }

    fun onClickCounter() {
        counter++
        coins++
    }

    fun refreshUiState() {
        view.showTextCounter(counter, coins)
        changeButtonPic()
    }

    fun onAbuseCoin() {
        i++
        when {
            i == 1 -> coins += 100
            i == 2 -> coins += 1000
            i == 3 -> coins += 10000
            i == 4 -> coins += 100000
            i > 4 -> {
                i = 1
                coins -= 111100
                view.tooMuchAbuse()
            }
        }
        refreshUiState()
    }

    fun saveState() {
        model.putCoins(coins)
        model.putCounter(counter)
    }

    private fun changeButtonPic() {
        when (counter) {
            100 -> view.setButtonPic(R.drawable.image1)
            200 -> view.setButtonPic(R.drawable.image2)
        }
    }
}