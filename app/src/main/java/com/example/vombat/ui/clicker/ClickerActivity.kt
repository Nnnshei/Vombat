package com.example.vombat.ui.clicker

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vombat.R
import com.example.vombat.model.db.coin.ClickerModelImpl
import com.example.vombat.presenter.clicker.ClickerPresenter
import kotlinx.android.synthetic.main.activity_clicker.*

class ClickerActivity : AppCompatActivity(), ClickerView {

    private val model by lazy {
        val APP_PREFENCES = "mydata"
        ClickerModelImpl(getSharedPreferences(APP_PREFENCES, Context.MODE_PRIVATE))
    }

    private val presenter by lazy {
        ClickerPresenter(this, model)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clicker)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        btnCount.setOnClickListener {
            presenter.onClickCounter()
            presenter.refreshUiState()
        }
        abuseCoin.setOnClickListener {
            presenter.onAbuseCoin()
        }
    }

    override fun showTextCounter(counter: Int, coins: Int) {
        textCounter.text = getString(R.string.counter_text, counter)
        textCoin.text = getString(R.string.coins_text, coins)
    }

    override fun setButtonPic(resource: Int) {
        btnCount.setImageResource(resource)
    }

    override fun tooMuchAbuse() {
        Toast.makeText(
            this,
            "Отложив в скорби о том, чего у вас нет, научимся воздавать благодарность за то, что есть.",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onPause() {
        super.onPause()
        presenter.saveState()
    }

    override fun onResume() {
        super.onResume()
        presenter.fetchData()
        presenter.refreshUiState()
    }
}