package com.article.feature.search.ui.adapter

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.article.R
import com.article.core.ui.model.ItemUserView
import com.article.databinding.ItemSearchUsersBinding

class AdapterSearchUser(val onItemClickListener: (ItemUserView) -> Unit) :
    ListAdapter<ItemUserView, AdapterSearchUser.ItemViewHolder>(object :
        DiffUtil.ItemCallback<ItemUserView>() {
        override fun areItemsTheSame(
            oldItem: ItemUserView,
            newItem: ItemUserView
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ItemUserView,
            newItem: ItemUserView
        ): Boolean =
            oldItem == newItem
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            DataBindingUtil
                .inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_search_users,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder( val binding: ItemSearchUsersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemUserView) {


            itemView.setOnClickListener {
                onItemClickListener(item)
            }
        }
    }
}