package com.example.vombat

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.videoView
import kotlinx.android.synthetic.main.activity_media.*
import java.net.URI

class MediaActivity : AppCompatActivity() {

    private val GALLERY_REQUEST: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media)
        videoView.setVideoURI(Uri.parse("https://2ch.hk/b/src/208059827/15745430231050.mp4"))
        btnVideo.setOnClickListener {
            chooseVideoFromGallery()
        }
    }

    private fun chooseVideoFromGallery() {
        val galleryIntent = Intent(
        Intent.ACTION_PICK,
        MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        )
        galleryIntent.type = ("vide/*")
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
