package com.article.feature.profile.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.article.R
import com.article.core.common.appSnackBar
import com.article.core.common.safeNavigate
import com.article.core.ui.AdapterViewPager
import com.article.databinding.FragmentProfileBinding
import com.article.feature.profile.ui.viewmodel.ProfileViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val fragmentList = listOf(MyArticleFragment(), BookmarksFragment())
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var binding: FragmentProfileBinding
    private lateinit var myArticleAdapter: MyArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserve()
    }

    override fun onResume() {
        super.onResume()
        binding.tabLayoutProfileFragment.setScrollPosition(
            binding.viewPagerProfileFrag.currentItem,
            0F,
            true
        )
    }

    private fun initObserve() {
        binding.profileViewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.snackBarMessage.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                requireView().appSnackBar(it.toString())
            }
        }

        viewModel.articleListOther.observe(viewLifecycleOwner) {
                iniRecycler()
                myArticleAdapter.submitList(it)
        }
    }

    private fun iniRecycler() {
        binding.rvProfileFragment.visibility=View.VISIBLE
        binding.tabLayoutProfileFragment.visibility=View.GONE
        binding.viewPagerProfileFrag.visibility=View.GONE
        myArticleAdapter = MyArticleAdapter(onItemArticleClickListener = {
            safeNavigate(
                ProfileFragmentDirections.actionProfileFragmentToDetailArticleFragment(it.id)
            )
        })
        binding.rvProfileFragment.apply {
            adapter = myArticleAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun initView() {
        val viewPagerAdapter = AdapterViewPager(fragmentList, childFragmentManager, lifecycle)
        binding.viewPagerProfileFrag.adapter = viewPagerAdapter
        binding.viewPagerProfileFrag.offscreenPageLimit = 1
        TabLayoutMediator(
            binding.tabLayoutProfileFragment,
            binding.viewPagerProfileFrag
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = resources.getText(R.string.my_article)
                    tab.setIcon(R.drawable.ic_list_task_graduate)
                }
                1 -> {
                    tab.text = resources.getText(R.string.bookmark)
                    tab.setIcon(R.drawable.ic_bookmark_favorite)
                }
                else -> {}
            }
        }.attach()
    }
}