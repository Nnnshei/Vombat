package com.example.vombat.ui.browser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vombat.R
import kotlinx.android.synthetic.main.activity_browser.*

class BrowserActivity : AppCompatActivity() {

    //private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)
        //webView = findViewById(R.id.webview)
        //webView.settings.javaScriptEnabled
        webview.loadUrl("https://yandex.ru")

    }
}

/* class MyWebViewClient: WebViewClient(){

 override fun shouldOverideUrlLoading(v: WebView?, url: String?, request: WebResourceRequest?): Boolean{
     view?.loadUrl(request?.url.toString())
     return true
 }
}
*/