package com.example.vombat.ui.test

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.vombat.R
import kotlinx.android.synthetic.main.activity_test.*

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