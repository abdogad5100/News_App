package com.example.banana_news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.banana_news.model.ArticlesItem

class NewsAdapter(var items: List<ArticlesItem>?):RecyclerView.Adapter<NewsAdapter.ViewHolder>() {




    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val title:TextView = itemView.findViewById(R.id.title)
        val author:TextView = itemView.findViewById(R.id.auther)
        val time:TextView = itemView.findViewById(R.id.time)
        val image:ImageView = itemView.findViewById(R.id.image)






    }





    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.title.setText(item?.title)
        holder.author.setText(item?.author)
        holder.time.setText(item?.publishedAt)
        Glide.with(holder.itemView)
            .load(item?.urlToImage)
            .into(holder.image)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent , false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items?.size ?:0
    }

    fun changeItem(articles: List<ArticlesItem?>?) {
            items = articles as List<ArticlesItem>?
            notifyDataSetChanged()


    }


}