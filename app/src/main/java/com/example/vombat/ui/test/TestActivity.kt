package com.example.vombat.ui.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.vombat.R
import com.example.vombat.presenter.test.TestPresenter
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity(), TestView {

    private val txtColor = textColor.text.toString()

    private val presenter by lazy {
        TestPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        btnColor.setOnClickListener{
            presenter.colorSwitch(textColor.text.toString())
        }
    }

    override fun setBackgroundColor() {
        Toast.makeText(this,
            presenter.colorSwitch(textColor.text.toString()),
            Toast.LENGTH_LONG).show()
    }
}