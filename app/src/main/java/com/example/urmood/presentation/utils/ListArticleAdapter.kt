package com.example.urmood.presentation.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.urmood.R
import com.example.urmood.data.model.ArticleResponse

class ListArticleAdapter(private val listArticle: List<ArticleResponse?>?) :
    RecyclerView.Adapter<ListArticleAdapter.ViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: ArticleResponse?)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var tvTitle: TextView = itemView.findViewById(R.id.tvArticleTitle)
        var tvBody: TextView = itemView.findViewById(R.id.tvArticleBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listArticle!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvBody.text = listArticle!![position]!!.body
        holder.tvTitle.text = listArticle[position]!!.title
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listArticle[holder.adapterPosition])
        }
    }
}