package com.article.feature.profile.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.article.R
import com.article.core.ui.model.ItemArticleView
import com.article.databinding.ItemHomeArticleBinding

class BookmarkAdapter(
    private val onItemArticleClickListener: (ItemArticleView) -> Unit
) :
    ListAdapter<ItemArticleView, BookmarkAdapter.ArticleHolder>(object :
        DiffUtil.ItemCallback<ItemArticleView>() {
        override fun areItemsTheSame(oldItem: ItemArticleView, newItem: ItemArticleView): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ItemArticleView,
            newItem: ItemArticleView
        ): Boolean {
            return oldItem == newItem
        }
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        return ArticleHolder(
            DataBindingUtil.inflate
                (
                LayoutInflater.from(parent.context),
                R.layout.item_home_article,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        holder.itemHomeArticleBinding.sampleArticle = getItem(position)

    }

    inner class ArticleHolder(val itemHomeArticleBinding: ItemHomeArticleBinding) :
        RecyclerView.ViewHolder(itemHomeArticleBinding.root) {

        init {
            itemView.setOnClickListener {
                onItemArticleClickListener(getItem(adapterPosition))
            }
        }
    }
}
