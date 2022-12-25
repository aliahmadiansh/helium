package com.article.feature.profile.ui.view
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.article.R
import com.article.databinding.ItemMyArticleBinding

class MyArticleAdapter(
    private val onItemArticleClickListener: (ProfileArticleView) -> Unit
) :
    ListAdapter<ProfileArticleView, MyArticleAdapter.ArticleHolder>(object :
        DiffUtil.ItemCallback<ProfileArticleView>() {
        override fun areItemsTheSame(
            oldItem: ProfileArticleView,
            newItem: ProfileArticleView
        ): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(
            oldItem: ProfileArticleView,
            newItem: ProfileArticleView
        ): Boolean {
            return oldItem == newItem
        }
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        return ArticleHolder(
            DataBindingUtil.inflate
                (
                LayoutInflater.from(parent.context),
                R.layout.item_my_article,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        holder.itemMyArticleBinding.sampleArticle = getItem(position)

    }

    inner class ArticleHolder(val itemMyArticleBinding: ItemMyArticleBinding) :
        RecyclerView.ViewHolder(itemMyArticleBinding.root) {
        init {
            itemView.setOnClickListener {
                onItemArticleClickListener(getItem(adapterPosition))
            }
        }
    }
}


