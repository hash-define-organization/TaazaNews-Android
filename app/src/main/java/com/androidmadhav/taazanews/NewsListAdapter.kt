package com.androidmadhav.taazanews

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_news_2.view.*


class NewsListAdapter( private val listener: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {
    private val items: ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {


        // view toh hai ki ek layout inflator se xml ko view me convert kia.
        // jo mere device/activity se applicable layout inflator hai from wo obtain krega aur return krega
        //
        val view = listener.getLayoutInflater().inflate(R.layout.item_news_2, parent, false)
        Log.d("Resource ID", R.layout.item_news.toString())
        val viewHolder =  NewsViewHolder(view)

        // yaha listener lagata hu aur color change krta hu toh poore holder me change hoga,
        // recycle krne ke baad bhi changed hi rhega

//        view.setOnClickListener({
//            if(viewHolder.adapterPosition > 25) {
//                view.findViewById<TextView>(R.id.title).setTextColor(584858)
//            }
//            else {
//                Toast.makeText(parent.context, "below 25", Toast.LENGTH_LONG).show()
//            }
//        })

        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

//        yaha pr lagane pr bhi holder ke andr, mtlb change retained rehta hai.
//        holder.itemView.setOnClickListener {
//            if (holder.adapterPosition > 25) {
//                holder.itemView.findViewById<TextView>(R.id.title).setTextColor(584858)
//            }
//        }

        val currentItem = items[position]
        holder.titleView.text = currentItem.title
        Glide.with(holder.itemView).load(currentItem.imageUrl).into(holder.imageView)
    }

    override fun getItemCount() = items.size

    fun updateNews(updatedNews: ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val titleView: TextView = itemView.findViewById(R.id.title)
    val imageView: ImageView = itemView.findViewById(R.id.image)
}

interface NewsItemClicked{
    fun onItemClicked(item: News)
    fun getLayoutInflater(): LayoutInflater
}