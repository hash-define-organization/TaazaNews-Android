package com.androidmadhav.taazanews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.androidmadhav.taazanews.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.NewsRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        fetchData()
        mAdapter = NewsListAdapter( this)
        binding.NewsRecycler.adapter = mAdapter
    }

    private fun fetchData() {

        val url = "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            {
                val allNewsInJson = it.getJSONArray("articles")
                val newsArray = ArrayList<News>()
                for(idx in 0 until allNewsInJson.length()){

                    val singleNewsInJson = allNewsInJson.optJSONObject(idx)
                    val newsInNeededFormat = News(
                        singleNewsInJson.getString("title"),
                        singleNewsInJson.getString("author"),
                        singleNewsInJson.getString("url"),
                        singleNewsInJson.getString("urlToImage")
                        )
                    newsArray.add(newsInNeededFormat)
                }

                mAdapter.updateNews(newsArray)

            },
            {

            }

        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }


    override fun onItemClicked(item: News) {
        val intent = Intent(this, NewsDetails::class.java)
            .putExtra("news", item)

        startActivity(intent)
    }
}