package com.example.vombat.presenter.test

import com.example.vombat.ui.test.TestView

class TestPresenter(private val view: TestView) {


    fun colorSwitch(textColor: String): String {
        return ("#${textColor}")
        view.setBackgroundColor()
    }
}