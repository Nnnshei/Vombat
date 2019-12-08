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

        }
        btnBrowser.setOnClickListener{

        }
        btnJopa.setOnClickListener{

        }
        btnTest.setOnClickListener{

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

    fun switchClicker(view: View) {
        when (view.id) {
            R.id.btnBrowser -> startActivity(Intent(this, BrowserActivity::class.java))
            R.id.btnClicker -> startActivity(Intent(this, ClickerActivity::class.java))
            R.id.btnJopa -> Toast.makeText(this, "Ещё не готово", Toast.LENGTH_LONG).show()
            R.id.btnTest -> startActivity(Intent(this, TestActivity::class.java))
        }
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


/*package com.example.vombat

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_clicker.*
import kotlinx.android.synthetic.main.activity_main.*

class ClickerActivity : AppCompatActivity() {


    private lateinit var pref: SharedPreferences
    private val APP_PREFERENCES = "mydata"
    private val APP_PREFERENCES_COUNTER = "counter"
    private val APP_PREFERENCES_COIN = "coin"
    var counter: Int = 0
    var coin: Int = 0
    var i: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clicker)
        pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        showTextCounter.text = "Ты нажал на кнопку ${counter} раз"
        textCoin.text = "У тебя ${coin} монет"
        if (counter >= 100 && counter < 250)
            buttonCount.setImageResource(R.drawable.image1)
        if (counter >= 250)
            buttonCount.setImageResource(R.drawable.image2)
    }

    fun clickCounter(view: View) {
        counter++
        coin++
        showTextCounter.text = "Ты нажа на кнопку ${counter} раз"
        textCoin.text = "У тебя ${coin} монет"
        if (counter >= 100 && counter < 250)
            buttonCount.setImageResource(R.drawable.image1)
        if (counter >= 250)
            buttonCount.setImageResource(R.drawable.image2)
    }

    fun abuseCoin(view: View) {
        i++
        if (i == 1)
            coin += 100
        textCoin.text = "У тебя ${coin} монет"
        if (i == 2)
            coin += 1000
        textCoin.text = "У тебя ${coin} монет"
        if (i == 3)
            coin += 10000
        textCoin.text = "У тебя ${coin} монет"
        if (i == 4)
            coin += 10000
        textCoin.text = "У тебя ${coin} монет"
        if (i > 4) {
            i = 1
            coin -= 111100
            textCoin.text = "У тебя ${coin} монет"

            Toast.makeText(
                this,
                "Отложив в скорби о том, чего у вас нет, научимся воздавать благодарность за то, что есть.",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    override fun onPause() {
        super.onPause()
        val editor = pref.edit()
        editor.putInt(APP_PREFERENCES_COIN, coin)
        editor.apply()
        editor.putInt(APP_PREFERENCES_COUNTER, counter)
        editor.apply()
    }

    override fun onResume() {
        super.onResume()
        if (pref.contains(APP_PREFERENCES_COIN))
            coin = pref.getInt(APP_PREFERENCES_COIN, 0)
        textCoin.text = "У тебя ${coin} монет"

        if (pref.contains(APP_PREFERENCES_COUNTER))
            counter = pref.getInt(APP_PREFERENCES_COUNTER, 0)
        showTextCounter.text = "Ты нажал на кнопку ${counter} раз"
    }
}
*/