package com.article.feature.search.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.article.core.common.appSnackBar
import com.article.core.ui.AdapterViewPager
import com.article.databinding.FragmentSearchBinding
import com.article.feature.search.ui.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserve()
    }

    private fun initView() {
        binding.searchViewModel = viewModel
        binding.lifecycleOwner = this

        val fragments =
            listOf(SearchPostFragment(), SearchTagFragment(), SearchUserFragment())

        val viewpagerAdapter = AdapterViewPager(fragments, childFragmentManager, lifecycle)

        binding.viewPagerSearchFragment.apply {
            adapter = viewpagerAdapter
            registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when (position) {
                        0 -> binding.chipPostSearchFragment.performClick()
                        1 -> binding.chipTagsSearchFragment.performClick()
                        2 -> binding.chipUsersSearchFragment.performClick()
                    }
                }
            })
        }
    }

    private fun initObserve() {
        viewModel.errorMessage.observe(viewLifecycleOwner) { list ->
            val message = buildString {
                list.forEach {
                    if (this.isEmpty())
                        this.append(it)
                    else
                        this.append("\n$it")
                }
            }
            requireView().appSnackBar(message)
        }

        viewModel.searchState.observe(viewLifecycleOwner) {
            when (it ?: SearchViewModel.SearchState.POST) {
                SearchViewModel.SearchState.POST -> binding.viewPagerSearchFragment.setCurrentItem(
                    0,
                    true
                )
                SearchViewModel.SearchState.TAG -> binding.viewPagerSearchFragment.setCurrentItem(
                    1,
                    true
                )
                SearchViewModel.SearchState.USER -> binding.viewPagerSearchFragment.setCurrentItem(
                    2,
                    true
                )
            }
        }
    }
}