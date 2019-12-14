package com.example.vombat.ui.main

import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vombat.ui.browser.BrowserActivity
import com.example.vombat.ui.clicker.ClickerActivity
import com.example.vombat.ui.media.MediaActivity
import com.example.vombat.R
import com.example.vombat.ui.test.TestActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        videoView.setVideoURI(Uri.parse("https://2ch.hk/b/src/208059827/15745430231050.mp4"))
        //videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() +"/"+R.raw.video_test))
        // videoView.setMediaController(MediaController(this))
        videoView.start()
//        videoView.seekTo(1)
        videoView.setOnCompletionListener {
            videoView.start()
        }
        btnClicker.setOnClickListener{
            startActivity(Intent(this, ClickerActivity::class.java))
        }
        btnBrowser.setOnClickListener{
            startActivity(Intent(this, BrowserActivity::class.java))
        }
        btnMedia.setOnClickListener{
            startActivity(Intent(this, MediaActivity::class.java))
        }
        btnTest.setOnClickListener{
            startActivity(Intent(this, TestActivity::class.java))
        }
    }



    override fun onResume() {
        super.onResume()
        videoView.start()
    }

}