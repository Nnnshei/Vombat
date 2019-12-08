package com.example.vombat

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.MediaController
import android.widget.Toast
import androidx.core.net.toFile
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val GALLERY_REQUEST: Int = 1
    var pause: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        videoView.setVideoURI(Uri.parse("https://2ch.hk/b/src/208059827/15745430231050.mp4"))
        //videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() +"/"+R.raw.video_test))
        // videoView.setMediaController(MediaController(this))
        videoView.pause()
        videoView.seekTo(1)
        videoView.setOnClickListener{
            switch()
        }
        videoView.setOnCompletionListener {
            videoView.start()
        }
        btnClicker.setOnClickListener{
            startActivity(Intent(this,ClickerActivity::class.java))
        }
        btnBrowser.setOnClickListener{
            startActivity(Intent(this,BrowserActivity::class.java))
        }
        btnJopa.setOnClickListener{
            startActivity(Intent(this,MediaActivity::class.java))
        }
        btnTest.setOnClickListener{
            startActivity(Intent(this,TestActivity::class.java))
        }
    }

    private fun switch() {
        pause = if (pause) {
            videoView.start()
            false
        } else {
            videoView.pause()
            true
        }
    }

    private fun chooseVideoFromGallery() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        )
        galleryIntent.type = "video/*"
        startActivityForResult(galleryIntent, GALLERY_REQUEST)
    }

    private fun getPath(uri: Uri): String? {
        val projection = arrayOf(MediaStore.Video.Media.DATA)
        val cursor = contentResolver.query(uri, projection, null, null, null)
        var returnString: String? = null
        if (cursor != null) {
            val column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            cursor.moveToFirst()
            returnString = cursor.getString(column_index)
            cursor.close()
        } else
            returnString = null
        return returnString
    }

    override fun onResume() {
        super.onResume()
        videoView.start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_REQUEST) {
            val video = data?.data
//            videoView.setVideoURI(Uri.fromFile(data?.data?.toFile()))
            videoView.setVideoURI(Uri.parse("file://${video?.let { getPath(it) }}"))
            //imageView.setImageResource("file://$video")
        }
    }

}