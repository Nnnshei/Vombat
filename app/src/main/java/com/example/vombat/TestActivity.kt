package com.example.vombat

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.text.style.BackgroundColorSpan
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_test.*
import kotlin.random.Random

class TestActivity : AppCompatActivity() {

    // val GALLERY_REQUEST: Int = 1
    lateinit var colorBkgrnd: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        btnColor.setOnClickListener{
            switchColor()
        }
    }

    fun switchColor() {
        colorBkgrnd = textColor.text.toString()
        Toast.makeText(this, "Jopa", Toast.LENGTH_LONG).show()
//        view.setBackgroundColor(Color.parseColor(colorBkgrnd))
        testLayout.setBackgroundColor(Color.parseColor(colorBkgrnd))
    }
}