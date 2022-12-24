package com.article.core.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class AdapterViewPager(private val fragmentList: List<Fragment>,
                       fragmentManager: FragmentManager,
                       fragmentLifecycle: Lifecycle):
    FragmentStateAdapter(fragmentManager,fragmentLifecycle) {
    override fun getItemCount(): Int = fragmentList.size
    override fun createFragment(position: Int): Fragment = fragmentList[position]
}