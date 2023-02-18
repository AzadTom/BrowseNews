package com.example.informed.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.informed.Utility.Constant
import com.example.informed.databinding.ActivityWebviewBinding
import java.net.URL

class Webview : AppCompatActivity() {

    private lateinit var binding: ActivityWebviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()



        val url = intent.getStringExtra(Constant.URL)



        binding.webview.apply {

            webViewClient = WebViewClient()
             loadUrl(url!!)


        }



    }
}