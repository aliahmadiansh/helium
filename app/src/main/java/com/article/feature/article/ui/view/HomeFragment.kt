package com.article.feature.article.ui.view

import com.article.feature.article.ui.viewmodel.HomeViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.article.R
import com.article.core.common.safeNavigate
import com.article.core.ui.model.ItemTagView
import com.article.databinding.FragmentHomeBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserve()
        initClickListener()
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchArticles()
    }

    private fun initObserve() {

        viewModel.articleList.observe(viewLifecycleOwner) { list ->
            articleAdapter.submitList(list)
            if (list.isNullOrEmpty()) {
                binding.tvNotFoundHomeFragment.visibility = View.VISIBLE
            } else {
                binding.tvNotFoundHomeFragment.visibility = View.GONE
            }
            viewModel.progressShow.value = false
        }

        viewModel.tagsViewFilter.observe(viewLifecycleOwner) {
            binding.chipGroupTagHomeFragment.removeAllViews()
            if (!it.isNullOrEmpty()) {
                initChip(it)
            } else {
                viewModel.fetchArticles()
            }
        }
    }


    private fun initView() {
        binding.apply {
            homeViewModel = viewModel
            lifecycleOwner = this@HomeFragment
        }

        articleAdapter = ArticleAdapter {
            safeNavigate(HomeFragmentDirections.actionHomeFragmentToDetailArticleFragment(it.id))
        }

        binding.recyclerViewHomeFragment.apply {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
        }

    }
//    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
    private fun initClickListener() {
        binding.btnAddArticleHomeFragment.setOnClickListener {
            safeNavigate(HomeFragmentDirections.actionHomeFragmentToPublishArticleFragment())
        }

        binding.btnTagHomeFragment.setOnClickListener {
            HomeTagBottomSheetFragment().show(
                childFragmentManager,
                HomeTagBottomSheetFragment.TAG
            )
        }
    }

    private fun initChip(tags: List<ItemTagView>) {
        tags.forEach { itemTagView ->
            val chip = Chip(requireContext())
            val drawable = ChipDrawable
                .createFromAttributes(
                    requireContext(),
                    null,
                    0,
                    R.style.MyChipHomeChecked
                )
            chip.apply {
                id = itemTagView.id
                text = itemTagView.name
                setChipDrawable(drawable)
                setOnClickListener {
                    viewModel.tagsViewFilter.value?.remove(itemTagView)
                    viewModel.tagsViewFilter.value = viewModel.tagsViewFilter.value
                }
            }
            binding.chipGroupTagHomeFragment.addView(chip)
        }
    }
}