package com.androidmadhav.taazanews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var mAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NewsRecycler.layoutManager = LinearLayoutManager(this)

        fetchData()
        mAdapter = NewsListAdapter( this)
        NewsRecycler.adapter = mAdapter
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
        TODO("Not yet implemented")
    }
}