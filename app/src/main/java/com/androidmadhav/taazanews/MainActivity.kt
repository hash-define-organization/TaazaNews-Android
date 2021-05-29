package com.androidmadhav.taazanews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NewsItemClicked {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NewsRecycler.layoutManager = LinearLayoutManager(this)

        val items = fetchData()
        val adapter = NewsListAdapter(items, this)
        NewsRecycler.adapter = adapter
    }

    private fun fetchData(): ArrayList<News> {
        val newsArray = ArrayList<News>()
        val url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=076e6ff77c764d6f9bd37ee5bc30a27c"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            {
                val allNewsInJson = it.getJSONArray("articles")

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
            },
            {

            }

        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
        return newsArray

    }

    override fun onItemClicked(item: News) {
        Toast.makeText(this, "Clicked $item", Toast.LENGTH_SHORT).show()
    }
}