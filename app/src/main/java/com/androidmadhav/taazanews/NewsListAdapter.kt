package com.androidmadhav.taazanews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class NewsListAdapter(private val items: ArrayList<News>, private val listner: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        val viewHolder =  NewsViewHolder(view)
        view.setOnClickListener {

            listner.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.toString()
    }

    override fun getItemCount() = items.size
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val titleView: TextView = itemView.findViewById<TextView>(R.id.title)
}

interface NewsItemClicked{
    fun onItemClicked(item: News)
}