package com.article.feature.search.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.article.R
import com.article.core.ui.model.ItemArticleView
import com.article.databinding.ItemHomeArticleBinding

class AdapterSearchPost(val onItemClickListener: (ItemArticleView) -> Unit) :
    ListAdapter<ItemArticleView, AdapterSearchPost.ItemViewHolder>(object :
        DiffUtil.ItemCallback<ItemArticleView>() {
        override fun areItemsTheSame(oldItem: ItemArticleView, newItem: ItemArticleView): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ItemArticleView, newItem: ItemArticleView): Boolean =
            oldItem == newItem
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            DataBindingUtil
                .inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_home_article,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder( val binding: ItemHomeArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemArticleView) {


            itemView.setOnClickListener {
                onItemClickListener(item)
            }
        }
    }
}

