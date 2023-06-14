package com.example.urmood.presentation.ui.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.urmood.R
import com.example.urmood.presentation.ui.ui.model.ArticleResponseItem

class ListArticleAdapter(private val listArticle: List<ArticleResponseItem?>?) : RecyclerView.Adapter<ListArticleAdapter.ViewHolder>() {
    class ViewHolder (itemview : View) : RecyclerView.ViewHolder(itemview) {
        var tvTitle : TextView = itemView.findViewById(R.id.tvArticleTitle)
        var tvBody : TextView = itemView.findViewById(R.id.tvArticleBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_article,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listArticle!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvBody.text = listArticle!![position]!!.body
        holder.tvTitle.text = listArticle[position]!!.title
    }
}