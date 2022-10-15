package com.androidmadhav.taazanews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.androidmadhav.taazanews.databinding.ActivityNewsDetailsBinding

class NewsDetails : AppCompatActivity() {

    private lateinit var binding: ActivityNewsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val news = intent.getSerializableExtra("news") as News
        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(news.url)
        }
    }
}